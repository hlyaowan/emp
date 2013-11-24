package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.common.ThreadConstant;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.CatalogInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.CatalogInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.ReadAppInfoUtil;
import com.quartz.monitor.util.ReadCatalogListUtil;

@Service
public class GetCatalogContentTimer {

    @Autowired
    private  AppInfoService appInfoService;
    @Autowired
    private  VisitUserService visitUserService;
    @Autowired
    private  CatalogInfoService catalogInfoService;
    
    //电信api接口
    private static ContentSeriveImpl contentService =new ContentSeriveImpl();
    
    private static Logger log = Logger.getLogger(GetCatalogContentTimer.class);


    public  void executeCatalogContent() {
        log.info("start GetCatalogContent ...");
        ReadAppInfoUtil util = new ReadAppInfoUtil();
        List<AppInfo> list = util.readAppInfoFile();
        AppInfo appInfo = util.getAppInfo(list);
        if(appInfo!=null){
//            CatalogInfo catalogInfo =catalogInfoService.getCatalogInfo(null);
            ReadCatalogListUtil readCatalogListUtil =new ReadCatalogListUtil();
            List<CatalogInfo> catalogInfos =readCatalogListUtil.readAppInfoFile();
            CatalogInfo catalogInfo =readCatalogListUtil.getCatalogInfo(catalogInfos);
            if(catalogInfo!=null){
                contentService.getCatalogContent(appInfo.appId, appInfo.accessToken, catalogInfo.catalogId, catalogInfo.start, catalogInfo.count);
                VisitUser user =new  VisitUser();
                user.mothodName="getCatalogContent";
                visitUserService.updateVisitUserNumber(user);
            }
        }
    }
    /***
     * 启动线程内容大概10w每天，一天86400s，每s执行约1.2次
     */
    public  void executeCatalogContentTask(){
        CatalogThread[] threads = new CatalogThread[ThreadConstant.CATALOGCONTENT_THREAD];
        for(CatalogThread thread : threads){
            thread = new CatalogThread();
            thread.start();
        }
    }
    
    class CatalogThread extends Thread{
        public void run(){
            long start =System.currentTimeMillis();
            executeCatalogContent();
            long end =System.currentTimeMillis();
            log.info("cost time:"+(end-start));
            try {
                Random random =new Random();
                int value =random.nextInt(ThreadConstant.SLEEPTIME)+1;
                Thread.sleep(value);
            }
            catch (InterruptedException e) {
                log.error("thread error:"+e);
            }
        }
    }
}
