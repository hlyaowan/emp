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
public class GetRecommendConfTimer {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;
    @Autowired
    private RecommendInfoService recommendInfoService;
    // 电信api接口
    private static RecommendServiceImpl recommendService = new RecommendServiceImpl();
    private static Logger log = Logger.getLogger(GetRecommendConfTimer.class);
    @Autowired
    private JedisClient jedisClient;

    public void executeRecommendConf() {
        log.info("start GetRecommendConfTimer ...");
        ReadAppInfoUtil util = new ReadAppInfoUtil();
        List<AppInfo> list = util.readAppInfoFile();
        AppInfo appInfo = util.getAppInfo(list);
        if (appInfo != null) {
            RecommendInfo recommendInfo = recommendInfoService.getRecommendInfo(null);
            if (recommendInfo != null) {
                String result=recommendService.getRecommendConf(appInfo.appId, appInfo.accessToken, recommendInfo.contentType, null,
                    recommendInfo.channelType, recommendInfo.timeType, recommendInfo.recommendType,
                    recommendInfo.start, recommendInfo.count);
                if(result!=null){
                    VisitUser user = new VisitUser();
                    user.mothodName = "getRecommendConf";
                    visitUserService.updateVisitUserNumber(user);
                }
              
            }
        }
    }


    /***
     * 启动线程
     */
    public void executeRecommendConfTask() {
        RecommendConfThread[] threads = new RecommendConfThread[10];
        for (RecommendConfThread thread : threads) {
            thread = new RecommendConfThread();
            thread.start();
        }
    }

    class RecommendConfThread extends Thread {
        public void run() {
            executeRecommendConf();
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
