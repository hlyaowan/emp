package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.common.ThreadConstant;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RecommendServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.ReadAppInfoUtil;


@Service
public class GetHotRecommendTimer {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;

    // 电信api接口
    private static RecommendServiceImpl recommendService = new RecommendServiceImpl();
    private static Logger log = Logger.getLogger(GetHotRecommendTimer.class);


    public void executeGetHotRecommend() {
        log.info("start GetHotRecommendTimer ...");

        ReadAppInfoUtil util = new ReadAppInfoUtil();
        List<AppInfo> list = util.readAppInfoFile();
        AppInfo appInfo = util.getAppInfo(list);
        if (appInfo != null) {
            recommendService.getHotRecommend(appInfo.appId, appInfo.accessToken);
            VisitUser user = new VisitUser();
            user.mothodName = "getHotRecommend";
            visitUserService.updateVisitUserNumber(user);
        }
    }


    /***
     * 启动线程每天8.64/3约2.2w       一天约8.6 w秒 ，每s约0.25次
     */
    public void executeGetHotRecommendTask() {
        HotRecommendThread[] threads = new HotRecommendThread[ThreadConstant.HOTRECOMMEND_THREAD];
        for (HotRecommendThread thread : threads) {
            thread = new HotRecommendThread();
            thread.start();
        }
    }

    class HotRecommendThread extends Thread {
        public void run() {
            executeGetHotRecommend();
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
