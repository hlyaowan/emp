package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;

import com.quartz.monitor.common.RedisConstant;
import com.quartz.monitor.common.ThreadConstant;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.RankInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RankTypeServiceImpl;
import com.quartz.monitor.jedis.JedisClient;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.RankInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.ReadAppInfoUtil;


@Service
public class GetRankTimer {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;
    @Autowired
    private RankInfoService rankInfoService;
    // 电信api接口
    private static RankTypeServiceImpl rankService = new RankTypeServiceImpl();
    private static Logger log = Logger.getLogger(GetRankTimer.class);
    @Autowired
    private JedisClient jedisClient;

    public void executeRank() {
        log.info("start GetRankTimer ...");
        
        
        ShardedJedis shardedJedis = jedisClient.getShardedJedis();
        try {
            ReadAppInfoUtil util = new ReadAppInfoUtil();
            List<AppInfo> list = util.readAppInfoFile();
            AppInfo appInfo = util.getAppInfo(list);
            if (appInfo != null) {
                RankInfo rankInfo = rankInfoService.getRankInfo(null);
                if (rankInfo != null) {
                    rankService.getRank(appInfo.appId, appInfo.accessToken, rankInfo.contentType, rankInfo.rankType,
                        rankInfo.rankTime, rankInfo.start, rankInfo.count);
                    VisitUser user = new VisitUser();
                    user.mothodName = "getRank";
                    visitUserService.updateVisitUserNumber(user);
                    //jedis计数器增加
                    jedisClient.incrTimeCount(shardedJedis, RedisConstant.RANK_KEY);
                }
            }
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        finally {
            if (shardedJedis != null) {
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /***
     * 启动线程8.6W /2=4.3W  每天8.64w秒 ，每s约0.5次
     */
    public void executeRankTask() {
        RankThread[] threads = new RankThread[ThreadConstant.RANK_THREAD];
        for (RankThread thread : threads) {
            thread = new RankThread();
            thread.start();
        }
    }

    class RankThread extends Thread {
        public void run() {
            executeRank();
            try {
                Random random =new Random();
                int value =random.nextInt(ThreadConstant.SLEEPTIME)+1;
                Thread.sleep(value);
            }
            catch (InterruptedException e) {
                log.error("thread error:"+e);
            }
        }
    }
}
