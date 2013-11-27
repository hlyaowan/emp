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
import com.quartz.monitor.entity.RecommendInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RecommendServiceImpl;
import com.quartz.monitor.jedis.JedisClient;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.RecommendInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.ReadAppInfoUtil;


@Service
public class GetRecommendTypeTimer {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;

    // 电信api接口
    private static RecommendServiceImpl recommendService = new RecommendServiceImpl();
    @Autowired
    private RecommendInfoService recommendInfoService;
    private static Logger log = Logger.getLogger(GetRecommendTypeTimer.class);
    @Autowired
    private JedisClient jedisClient;

    public void executeGetRecommendType() {
        log.info("start GetRecommendTypeTimer ...");

        
        
        ShardedJedis shardedJedis = jedisClient.getShardedJedis();
        try {
            ReadAppInfoUtil util = new ReadAppInfoUtil();
            List<AppInfo> list = util.readAppInfoFile();
            AppInfo appInfo = util.getAppInfo(list);
            if (appInfo != null) {
                recommendService.getRecommendType(appInfo.appId, appInfo.accessToken);
                VisitUser user = new VisitUser();
                user.mothodName = "getRecommendType";
                visitUserService.updateVisitUserNumber(user);
                //jedis计数器增加
                jedisClient.incrTimeCount(shardedJedis, RedisConstant.RECOMMENDTYPE_KEY);
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
     * 启动线程每天8.64/3约2.2w       一天约8.6 w秒 ，每s约0.25次
     */
    public void executeGetRecommendTypeTask() {
        RecommendThread[] threads = new RecommendThread[ThreadConstant.RECOMMENDTYPE_THREAD];
        for (RecommendThread thread : threads) {
            thread = new RecommendThread();
            thread.start();
        }
    }

    class RecommendThread extends Thread {
        public void run() {
            executeGetRecommendType();
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
