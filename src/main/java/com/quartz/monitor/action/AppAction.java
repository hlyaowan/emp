package com.quartz.monitor.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;

import com.opensymphony.xwork2.ActionSupport;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.jedis.JedisClient;
import com.quartz.monitor.serviceImpl.AppInfoServiceImpl;


@SuppressWarnings("serial")
public class AppAction extends ActionSupport {
    private static Logger log = Logger.getLogger(AppAction.class);
    private  List<AppInfo> appList = new ArrayList<AppInfo>();


    public List<AppInfo> getAppList() {
        return appList;
    }


    public void setAppList(List<AppInfo> appList) {
        this.appList = appList;
    }


    @Autowired
     private AppInfoServiceImpl appInfoService;

    @Autowired
    private JedisClient client;

    public String list() throws Exception {
        
        ShardedJedis shareclinet =client.getShardedJedis();
//        client.incrTimeCount(shareclinet, "author");
        System.out.println("===================="+client.getTimeCount(shareclinet, "qqq"));
        
        appList =appInfoService.getAppInfoList(null);
        log.info("AppInfo size:" + appList.size());
        return "list";
    }
}
