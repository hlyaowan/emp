package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.RankInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RankTypeServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.RankInfoService;
import com.quartz.monitor.service.VisitUserService;


public class GetRankTimer {
    @Autowired
    private static AppInfoService appInfoService;
    @Autowired
    private static VisitUserService visitUserService;
    @Autowired
    private static RankInfoService rankInfoService;
    //电信api接口
    private static RankTypeServiceImpl  rankService =new RankTypeServiceImpl();
    private static Logger log = Logger.getLogger(GetRankTimer.class);


    public static void executeRankTask() {
        log.info("start GetRankTimer ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if(appInfo!=null){
            RankInfo rankInfo =rankInfoService.getRankInfo(null);
            if(rankInfo!=null){
                String jsonString =rankService.getRank(appInfo.appId, appInfo.accessToken, rankInfo.contentType, rankInfo.rankType, rankInfo.rankTime, rankInfo.start, rankInfo.count);
                if(StringUtils.isEmpty(jsonString)){
                    VisitUser user =new  VisitUser();
                    user.mothodName="getRank";
                    visitUserService.updateVisitUserNumber(user);
                }
            }
        }
    }
}
