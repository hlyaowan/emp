package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.RecommendInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RecommendServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.RecommendInfoService;
import com.quartz.monitor.service.VisitUserService;


public class GetRecommendConfTimer {
    @Autowired
    private static AppInfoService appInfoService;
    @Autowired
    private static VisitUserService visitUserService;
    @Autowired
    private static RecommendInfoService recommendInfoService;
    // 电信api接口
    private static RecommendServiceImpl recommendService = new RecommendServiceImpl();
    private static Logger log = Logger.getLogger(GetRecommendConfTimer.class);


    public static void executeRecommendConfTask() {
        log.info("start GetRecommendConfTimer ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if (appInfo != null) {
            RecommendInfo recommendInfo =recommendInfoService.getRecommendInfo(null);
            if(recommendInfo!=null){
                String jsonString =
                        recommendService.getRecommendConf(appInfo.appId, appInfo.accessToken, recommendInfo.contentType, null, recommendInfo.channelType, recommendInfo.timeType,recommendInfo.recommendType, recommendInfo.start,
                            recommendInfo.count);
                if (StringUtils.isEmpty(jsonString)) {
                    VisitUser user = new VisitUser();
                    user.mothodName = "getRecommendConf";
                    visitUserService.updateVisitUserNumber(user);
                }
            }
        }
    }
}