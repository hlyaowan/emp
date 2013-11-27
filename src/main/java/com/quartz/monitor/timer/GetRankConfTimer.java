package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class GetRankConfTimer {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;
    @Autowired
    private RankInfoService rankInfoService;
    // 电信api接口
    private static RankTypeServiceImpl rankService = new RankTypeServiceImpl();
    private static Logger log = Logger.getLogger(GetRankConfTimer.class);
    @Autowired
    private JedisClient jedisClient;

    public void executeRankConf() {
        log.info("start GetRankConfTimer ...");
        ReadAppInfoUtil util = new ReadAppInfoUtil();
        List<AppInfo> list = util.readAppInfoFile();
        AppInfo appInfo = util.getAppInfo(list);
        if (appInfo != null) {
            RankInfo rankInfo = rankInfoService.getRankInfo(null);
            if (rankInfo != null) {
                rankService.getRankConf(appInfo.appId, appInfo.accessToken, rankInfo.contentType, null,
                    rankInfo.channelType, rankInfo.rankType, rankInfo.rankType, rankInfo.start, rankInfo.count);
                VisitUser user = new VisitUser();
                user.mothodName = "getRankConf";
                visitUserService.updateVisitUserNumber(user);
            }
        }
    }


    /***
     * 启动线程
     */
    public void executeRankConfTask() {
        RankConfThread[] threads = new RankConfThread[10];
        for (RankConfThread thread : threads) {
            thread = new RankConfThread();
            thread.start();
        }
    }

    class RankConfThread extends Thread {
        public void run() {
            executeRankConf();
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
