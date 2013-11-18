package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RecommendServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.RecommendInfoService;
import com.quartz.monitor.service.VisitUserService;


public class GetRecommendTypeTimer {
    @Autowired
    private static AppInfoService appInfoService;
    @Autowired
    private static VisitUserService visitUserService;
    
    //电信api接口
    private static RecommendServiceImpl recommendService =new RecommendServiceImpl();
    @Autowired
    private RecommendInfoService recommendInfoService;
    private static Logger log = Logger.getLogger(GetRecommendTypeTimer.class);


    public static void executeGetRecommendTask() {
        log.info("start GetRecommendTypeTimer ...");
        
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if(appInfo!=null){
            String jsonString =recommendService.getRecommendType(appInfo.appId, appInfo.accessToken);
            if(StringUtils.isEmpty(jsonString)){
                VisitUser user =new  VisitUser();
                user.mothodName="getRecommendType";
                visitUserService.updateVisitUserNumber(user);
            }
        }
    }
}
