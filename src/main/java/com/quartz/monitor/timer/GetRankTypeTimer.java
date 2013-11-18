package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RankTypeServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.VisitUserService;

@Service
public class GetRankTypeTimer {
    @Autowired
    private  AppInfoService appInfoService;
    @Autowired
    private  VisitUserService visitUserService;
    //电信api接口
    private static RankTypeServiceImpl  rankService =new RankTypeServiceImpl();
    private static Logger log = Logger.getLogger(GetRankTypeTimer.class);


    public  void executeRankTypeTask() {
        log.info("start GetRankTypeTimer ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if(appInfo!=null){
            String jsonString =rankService.getRankType(appInfo.appId, appInfo.accessToken);
            if(StringUtils.isNotEmpty(jsonString)){
                VisitUser user =new  VisitUser();
                user.mothodName="getRankType";
                visitUserService.updateVisitUserNumber(user);
            }
        }
    }
}
