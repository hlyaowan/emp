package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RecommendServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.VisitUserService;
@Service
public class GetHotRecommendTimer {
    @Autowired
    private  AppInfoService appInfoService;
    @Autowired
    private  VisitUserService visitUserService;
    
    //电信api接口
    private static RecommendServiceImpl recommendService =new RecommendServiceImpl();
    private static Logger log = Logger.getLogger(GetHotRecommendTimer.class);


    public  void executeGetHotRecommendTask() {
        log.info("start GetHotRecommendTimer ...");
        
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if(appInfo!=null){
            String jsonString =recommendService.getHotRecommend(appInfo.appId, appInfo.accessToken);
            if(StringUtils.isNotEmpty(jsonString)){
                VisitUser user =new  VisitUser();
                user.mothodName="getHotRecommend";
                visitUserService.updateVisitUserNumber(user);
            }
        }
    }
}
