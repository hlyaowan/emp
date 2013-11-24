package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.common.ThreadConstant;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.ContentInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.ContentInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.ReadAppInfoUtil;
import com.quartz.monitor.util.Tools;


@Service
public class GetChapterListTimer {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;
    @Autowired
    private ContentInfoService contentInfoService;
    // 电信api接口
    private static ContentSeriveImpl contentService = new ContentSeriveImpl();
    private static Logger log = Logger.getLogger(GetChapterListTimer.class);


    public void executeChapterList() {
        log.info("start getChapterListTimer ...");
        ReadAppInfoUtil util = new ReadAppInfoUtil();
        List<AppInfo> list = util.readAppInfoFile();
        AppInfo appInfo = util.getAppInfo(list);
        if (appInfo != null) {
            int i=53850;
            int j=169727;
            ContentInfo content =new ContentInfo();
            content.id =Tools.getRandom(i,j);
            ContentInfo contentInfo = contentInfoService.getContentInfo(content);
            if (contentInfo != null) {
                contentService.getChapterList(appInfo.appId, appInfo.accessToken, contentInfo.contentId,
                    contentInfo.start, contentInfo.count);
                VisitUser user = new VisitUser();
                user.mothodName = "getChapterList";
                visitUserService.updateVisitUserNumber(user);
            }

        }
    }
    

    /***
     * 启动线程章节列表;5.8W 一天86400s，每s 0.8次
     */
    public void executeChapterListTask() {
        ChapterThread[] threads = new ChapterThread[ThreadConstant.CHAPTERLIST_THREAD];
        for (ChapterThread thread : threads) {
            thread = new ChapterThread();
            thread.start();
        }
    }

    class ChapterThread extends Thread {
        public void run() {
            executeChapterList();
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
