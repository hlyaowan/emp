package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.CatalogInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.CatalogInfoService;
import com.quartz.monitor.service.VisitUserService;


public class GetCatalogContentTimer {

    @Autowired
    private static AppInfoService appInfoService;
    @Autowired
    private static VisitUserService visitUserService;
    @Autowired
    private static CatalogInfoService catalogInfoService;
    
    //电信api接口
    private static ContentSeriveImpl contentService =new ContentSeriveImpl();
    
    private static Logger log = Logger.getLogger(GetCatalogContentTimer.class);


    public static void executeCatalogContentTask() {
        log.info("start GetCatalogContent ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if(appInfo!=null){
            CatalogInfo catalogInfo =catalogInfoService.getCatalogInfo(null);
            if(catalogInfo!=null){
                String jsonString =contentService.getCatalogContent(appInfo.appId, appInfo.accessToken, catalogInfo.catalogId, catalogInfo.start, catalogInfo.count);
                if(StringUtils.isEmpty(jsonString)){
                    VisitUser user =new  VisitUser();
                    user.mothodName="getCatalogContent";
                    visitUserService.updateVisitUserNumber(user);
                }
            }
        }
    }
}
