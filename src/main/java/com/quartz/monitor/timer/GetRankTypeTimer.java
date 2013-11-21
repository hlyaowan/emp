package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RankTypeServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.ReadAppInfoUtil;


@Service
public class GetRankTypeTimer {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;
    // 电信api接口
    private static RankTypeServiceImpl rankService = new RankTypeServiceImpl();
    private static Logger log = Logger.getLogger(GetRankTypeTimer.class);


    public void executeRankType() {
        log.info("start GetRankTypeTimer ...");
        ReadAppInfoUtil util = new ReadAppInfoUtil();
        List<AppInfo> list = util.readAppInfoFile();
        AppInfo appInfo = util.getAppInfo(list);
        if (appInfo != null) {
            rankService.getRankType(appInfo.appId, appInfo.accessToken);
            VisitUser user = new VisitUser();
            user.mothodName = "getRankType";
            visitUserService.updateVisitUserNumber(user);
        }
    }


    /***
     * 启动线程启动线程8.6W /2=4.3W  每天8.64w秒 ，每s约0.5次
     */
    public void executeRankTypeTask() {
        RankTypeThread[] threads = new RankTypeThread[2];
        for (RankTypeThread thread : threads) {
            thread = new RankTypeThread();
            thread.start();
        }
    }

    class RankTypeThread extends Thread {
        public void run() {
            executeRankType();
            try {
                Random random =new Random();
                int value =random.nextInt(4000)+1;
                Thread.sleep(value);
            }
            catch (InterruptedException e) {
                log.error("thread error:"+e);
            }
        }
    }
}
