package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;

import com.quartz.monitor.common.RedisConstant;
import com.quartz.monitor.common.ThreadConstant;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.jedis.JedisClient;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.ReadAppInfoUtil;


@Service
public class GetChannelsTimer {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;
    @Autowired
    private JedisClient jedisClient;
    // 电信api接口
    private static ContentSeriveImpl contentService = new ContentSeriveImpl();
    private static Logger log = Logger.getLogger(GetChannelsTimer.class);


    public void executeChannels() {
        log.info("start GetChannels ...");
        
        
        ShardedJedis shardedJedis = jedisClient.getShardedJedis();
        try {
            ReadAppInfoUtil util = new ReadAppInfoUtil();
            List<AppInfo> list = util.readAppInfoFile();
            AppInfo appInfo = util.getAppInfo(list);
            if (appInfo != null) {
                String result= contentService.getChannels(appInfo.appId, appInfo.accessToken);
                if(result!=null){
                    VisitUser user = new VisitUser();
                user.mothodName = "getChannels";
                visitUserService.updateVisitUserNumber(user);
                //jedis计数器增加
                jedisClient.incrTimeCount(shardedJedis, RedisConstant.CHANNELS_KEY);
                }
                
            }
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        finally {
            if (shardedJedis != null) {
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /***
     * 启动线程//频道每天17.2W量，一天86400S，每s大约2次
     */
    public void executeChannelsTask() {
        ChannelsThread[] threads = new ChannelsThread[ThreadConstant.CHANNELS_THREAD];
        for (ChannelsThread thread : threads) {
            thread = new ChannelsThread();
            thread.start();
        }
    }

    class ChannelsThread extends Thread {
        public void run() {
            executeChannels();
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
