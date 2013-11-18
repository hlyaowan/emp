package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.VisitUserService;

@Service
public class GetChannelsTimer {
    @Autowired
    private  AppInfoService appInfoService;
    @Autowired
    private  VisitUserService visitUserService;
    
    //电信api接口
    private static ContentSeriveImpl contentService =new ContentSeriveImpl();
    private static Logger log = Logger.getLogger(GetChannelsTimer.class);


    public  void executeChannelsTask() {
        log.info("start GetChannels ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if(appInfo!=null){
            String jsonString =contentService.getChannels(appInfo.appId, appInfo.accessToken);
            if(StringUtils.isNotEmpty(jsonString)){
                VisitUser user =new  VisitUser();
                user.mothodName="getChannels";
                visitUserService.updateVisitUserNumber(user);
            }
        }
    }
}
