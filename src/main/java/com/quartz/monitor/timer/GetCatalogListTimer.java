package com.quartz.monitor.timer;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.VisitUserService;


public class GetCatalogListTimer {
    @Autowired
    private static AppInfoService appInfoService;
    @Autowired
    private static VisitUserService visitUserService;
    
    //电信api接口
    private static ContentSeriveImpl contentService =new ContentSeriveImpl();
    private static Logger log = Logger.getLogger(GetCatalogListTimer.class);


    public static void executeCatalogListTask() {
        log.info("start GetCatalogList ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if(appInfo!=null){
            Random random=new Random();
            int channelID =random.nextInt(4)+1;
            String jsonString =contentService.getCatalogList(appInfo.appId, appInfo.accessToken, String.valueOf(channelID));
            if(StringUtils.isEmpty(jsonString)){
                VisitUser user =new  VisitUser();
                user.mothodName="getCatalogList";
                visitUserService.updateVisitUserNumber(user);
            }
        }
    }
}
