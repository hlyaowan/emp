package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.ContentInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.ContentInfoService;
import com.quartz.monitor.service.VisitUserService;


public class GetContentInfoTimer {
    @Autowired
    private static AppInfoService appInfoService;
    @Autowired
    private static VisitUserService visitUserService;
    @Autowired
    private static ContentInfoService contentInfoService;
    //电信api接口
    private static ContentSeriveImpl contentService =new ContentSeriveImpl();
    private static Logger log = Logger.getLogger(GetContentInfoTimer.class);


    public static void executeContentInfoTask() {
        log.info("start GetContentInfoTimer ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if(appInfo!=null){
            ContentInfo contentInfo =contentInfoService.getContentInfo(null);
            if(contentInfo!=null){
                String jsonString =contentService.getContentInfo(appInfo.appId, appInfo.accessToken, contentInfo.contentId);
                if(StringUtils.isEmpty(jsonString)){
                    VisitUser user =new  VisitUser();
                    user.mothodName="getContentInfo";
                    visitUserService.updateVisitUserNumber(user);
                }
            }
           
        }
    }
}