package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.RecommendInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RecommendServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.RecommendInfoService;
import com.quartz.monitor.service.VisitUserService;

@Service
public class GetRecommendTimer {
    @Autowired
    private  AppInfoService appInfoService;
    @Autowired
    private  VisitUserService visitUserService;
    @Autowired
    private  RecommendInfoService recommendInfoService;
    //电信api接口
    private static RecommendServiceImpl recommendService =new RecommendServiceImpl();
    private static Logger log = Logger.getLogger(GetRecommendTimer.class);


    public  void executeGetRecommendTask() {
        log.info("start GetRecommendTimer ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if(appInfo!=null){
            RecommendInfo recommendInfo =recommendInfoService.getRecommendInfo(null);
            if(recommendInfo!=null){
                String jsonString =recommendService.getRecommend(appInfo.appId, appInfo.accessToken, recommendInfo.recommendType, recommendInfo.start, recommendInfo.count);
                if(StringUtils.isNotEmpty(jsonString)){
                    VisitUser user =new  VisitUser();
                    user.mothodName="getRecommend";
                    visitUserService.updateVisitUserNumber(user);
                }
            }
        }
    }
}
