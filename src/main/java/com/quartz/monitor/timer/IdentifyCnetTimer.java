package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.CnetServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.UaInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.DateUtil;
import com.quartz.monitor.util.ReadAppInfoUtil;


@Service
public class IdentifyCnetTimer {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private UaInfoService uaInfoService;
    @Autowired
    private VisitUserService visitUserService;
    // 电信api接口
    private static CnetServiceImpl cnetService = new CnetServiceImpl();
    private static Logger log = Logger.getLogger(IdentifyCnetTimer.class);


    public void executeIdentifyCnet() {
        log.info("start IdentifyCnetTimer ...");
        ReadAppInfoUtil util = new ReadAppInfoUtil();
        List<AppInfo> list = util.readAppInfoFile();
        AppInfo appInfo = util.getAppInfo(list);
        if (appInfo != null) {
            String timestamp = DateUtil.getCurrentTimestamp();
            cnetService.identifyCnet(appInfo.appId, appInfo.accessToken, null, genIp(), timestamp);
            VisitUser user = new VisitUser();
            user.mothodName = "identifyCnet";
            visitUserService.updateVisitUserNumber(user);
        }
    }


    /***
     * 启动线程 74.3W  每天86400S。每s 9次
     */
    public void executeIdentifyCnetTask() {
        IdentifyCnetThread[] threads = new IdentifyCnetThread[9];
        for (IdentifyCnetThread thread : threads) {
            thread = new IdentifyCnetThread();
            thread.start();
        }
    }

    class IdentifyCnetThread extends Thread {
        public void run() {
            executeIdentifyCnet();
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


    public String genIp() {
        Random random = new Random();
        int value = random.nextInt(255) + 1;
        int value2 = random.nextInt(255) + 1;
        int value3 = random.nextInt(255) + 1;
        int value4 = random.nextInt(255) + 1;
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append(value);
        sBuffer.append(".");
        sBuffer.append(value2);
        sBuffer.append(".");
        sBuffer.append(value3);
        sBuffer.append(".");
        sBuffer.append(value4);
        return sBuffer.toString();
    }


    public static void main(String[] args) {
        IdentifyCnetTimer identifyCnetTimer = new IdentifyCnetTimer();
        System.err.println(identifyCnetTimer.genIp());
    }
}
