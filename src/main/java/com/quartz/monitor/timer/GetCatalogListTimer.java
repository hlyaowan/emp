package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.common.ThreadConstant;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.ReadAppInfoUtil;

@Service
public class GetCatalogListTimer {
    @Autowired
    private  AppInfoService appInfoService;
    @Autowired
    private  VisitUserService visitUserService;
    
    //电信api接口
    private static ContentSeriveImpl contentService =new ContentSeriveImpl();
    private static Logger log = Logger.getLogger(GetCatalogListTimer.class);


    public  void executeCatalogList() {
        log.info("start GetCatalogList ...");
        ReadAppInfoUtil util = new ReadAppInfoUtil();
        List<AppInfo> list = util.readAppInfoFile();
        AppInfo appInfo = util.getAppInfo(list);
        if(appInfo!=null){
            Random random=new Random();
            int channelID =random.nextInt(4)+1;
            contentService.getCatalogList(appInfo.appId, appInfo.accessToken, String.valueOf(channelID));
            VisitUser user =new  VisitUser();
            user.mothodName="getCatalogList";
            visitUserService.updateVisitUserNumber(user);
        }
    }
    
    /***
     * 启动线程 //分栏每天14.2W量，一天86400S，每s大约1.5次
     */
    public  void executeCatalogListTask(){
        CatalogListThread[] threads = new CatalogListThread[ThreadConstant.CATALOGLIST_THREAD];
        for(CatalogListThread thread : threads){
            thread = new CatalogListThread();
            thread.start();
        }
    }
    
    class CatalogListThread extends Thread{
        public void run(){
            executeCatalogList();
            try {
                Random random =new Random();
                int value =random.nextInt(4000)+1;
                Thread.sleep(value);
            }
            catch (InterruptedException e) {
                log.error("thread error:"+e);
            }
        }
    }
}
