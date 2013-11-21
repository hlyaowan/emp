package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.AuthorInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.AuthorInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.ReadAppInfoUtil;


@Service
public class GetAuthorInfoTimer {

    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;
    @Autowired
    private AuthorInfoService authorInfoService;
    // 电信api接口
    private static ContentSeriveImpl contentService = new ContentSeriveImpl();
    private static Logger log = Logger.getLogger(GetAuthorInfoTimer.class);


    public void executeAuthorInfo() {
        log.info("start getAuthorTask ...");
        ReadAppInfoUtil util = new ReadAppInfoUtil();
        List<AppInfo> list = util.readAppInfoFile();
        AppInfo appInfo = util.getAppInfo(list);
        if (appInfo != null) {
            AuthorInfo authorInfo = authorInfoService.getAuthorInfo(null);
            if (authorInfo != null) {
                contentService.getAuthorInfo(appInfo.appId, appInfo.accessToken, authorInfo.authorId, authorInfo.start,
                    authorInfo.count);
                // 统计发送的接口次数
                VisitUser user = new VisitUser();
                user.mothodName = "getAuthorInfo";
                visitUserService.updateVisitUserNumber(user);
            }
        }
    }

   

    /**
     *  /* 内容5W，每天8.6w s，每s0.6次

      */

    public void executeAuthorInfoTask() {
        MyThread[] threads = new MyThread[1];
        for (MyThread thread : threads) {
            thread = new MyThread();
            thread.start();
        }
    }

    class MyThread extends Thread {
        public void run() {
            executeAuthorInfo();
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
