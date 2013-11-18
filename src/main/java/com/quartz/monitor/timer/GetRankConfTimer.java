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


public class GetRankConfTimer {
    @Autowired
    private static AppInfoService appInfoService;
    @Autowired
    private static VisitUserService visitUserService;
    @Autowired
    private static RankInfoService rankInfoService;
    // 电信api接口
    private static RankTypeServiceImpl rankService = new RankTypeServiceImpl();
    private static Logger log = Logger.getLogger(GetRankConfTimer.class);


    public static void executeRankConfTask() {
        log.info("start GetRankConfTimer ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if (appInfo != null) {
            RankInfo rankInfo =rankInfoService.getRankInfo(null);
            if(rankInfo!=null){
                String jsonString =
                        rankService.getRankConf(appInfo.appId, appInfo.accessToken, rankInfo.contentType, null, rankInfo.channelType, rankInfo.rankType, rankInfo.rankType,rankInfo.start, rankInfo.count);
                if (StringUtils.isEmpty(jsonString)) {
                    VisitUser user = new VisitUser();
                    user.mothodName = "getRankConf";
                    visitUserService.updateVisitUserNumber(user);
                }
            }
        }
    }
}
