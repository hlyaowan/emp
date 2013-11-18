package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.RankInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.RankTypeServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.RankInfoService;
import com.quartz.monitor.service.VisitUserService;

@Service
public class GetRankConfTimer {
    @Autowired
    private  AppInfoService appInfoService;
    @Autowired
    private  VisitUserService visitUserService;
    @Autowired
    private  RankInfoService rankInfoService;
    // 电信api接口
    private static RankTypeServiceImpl rankService = new RankTypeServiceImpl();
    private static Logger log = Logger.getLogger(GetRankConfTimer.class);


    public  void executeRankConfTask() {
        log.info("start GetRankConfTimer ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if (appInfo != null) {
            RankInfo rankInfo =rankInfoService.getRankInfo(null);
            if(rankInfo!=null){
                String jsonString =
                        rankService.getRankConf(appInfo.appId, appInfo.accessToken, rankInfo.contentType, null, rankInfo.channelType, rankInfo.rankType, rankInfo.rankType,rankInfo.start, rankInfo.count);
                if (StringUtils.isNotEmpty(jsonString)) {
                    VisitUser user = new VisitUser();
                    user.mothodName = "getRankConf";
                    visitUserService.updateVisitUserNumber(user);
                }
            }
        }
    }
}
