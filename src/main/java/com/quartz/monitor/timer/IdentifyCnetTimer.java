package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.Uagent;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.CnetServiceImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.UaInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.DateUtil;
@Service
public class IdentifyCnetTimer {
    @Autowired
    private  AppInfoService appInfoService;
    @Autowired
    private  UaInfoService uaInfoService;
    @Autowired
    private  VisitUserService visitUserService;
    //电信api接口
    private static CnetServiceImpl cnetService =new CnetServiceImpl();
    private static Logger log = Logger.getLogger(IdentifyCnetTimer.class);


    public  void executeIdentifyCnetTask() {
        log.info("start IdentifyCnetTimer ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if(appInfo!=null){
            Uagent uagent =uaInfoService.getUagentInfo(null);
            if(uagent!=null){
                String timestamp =DateUtil.getCurrentTimestamp();
                String jsonString =cnetService.identifyCnet(appInfo.appId, appInfo.accessToken, uagent.getUainfo(), uagent.getIp(), timestamp);
                if(StringUtils.isNotEmpty(jsonString)){
                    VisitUser user =new  VisitUser();
                    user.mothodName="identifyCnet";
                    visitUserService.updateVisitUserNumber(user);
                }
            }
        }
    }
}
