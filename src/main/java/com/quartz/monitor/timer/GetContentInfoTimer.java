package com.quartz.monitor.timer;

import java.util.List;
import java.util.Random;

import javax.tools.Tool;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;

import com.quartz.monitor.common.RedisConstant;
import com.quartz.monitor.common.ThreadConstant;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.ContentInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.jedis.JedisClient;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.ContentInfoService;
import com.quartz.monitor.service.VisitUserService;
import com.quartz.monitor.util.ReadAppInfoUtil;
import com.quartz.monitor.util.Tools;


@Service
public class GetContentInfoTimer {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;
    @Autowired
    private ContentInfoService contentInfoService;
    // 电信api接口
    private static ContentSeriveImpl contentService = new ContentSeriveImpl();
    private static Logger log = Logger.getLogger(GetContentInfoTimer.class);
    @Autowired
    private JedisClient jedisClient;


    public void executeContentInfo() {
        log.info("start GetContentInfoTimer ...");

        ShardedJedis shardedJedis = jedisClient.getShardedJedis();
        try {
            ReadAppInfoUtil util = new ReadAppInfoUtil();
            List<AppInfo> list = util.readAppInfoFile();
            AppInfo appInfo = util.getAppInfo(list);
            if (appInfo != null) {
                int i = 53850;
                int j = 169727;
                ContentInfo content = new ContentInfo();
                content.id = Tools.getRandom(i, j);
                ContentInfo contentInfo = contentInfoService.getContentInfo(content);
                if (contentInfo != null) {
                    String result =
                            contentService.getContentInfo(appInfo.appId, appInfo.accessToken, contentInfo.contentId);
                    if (result != null) {
                        VisitUser user = new VisitUser();
                        user.mothodName = "getContentInfo";
                        visitUserService.updateVisitUserNumber(user);
                        // jedis计数器增加
                        jedisClient.incrTimeCount(shardedJedis, RedisConstant.CONTENTINFO_KEY);
                    }

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
     * 启动线程内容大概5.8w每天，一天86400s，每s执行约1次
     */
    public void executeContentInfoTask() {
        ContentThread[] threads = new ContentThread[ThreadConstant.CONTENTINFO_THREAD];
        for (ContentThread thread : threads) {
            thread = new ContentThread();
            thread.start();
        }
    }

    /***
     * 
     * @author hlyaowan
     * 
     */
    class ContentThread extends Thread {
        public void run() {
            executeContentInfo();
            try {
                Random random = new Random();
                int value = random.nextInt(ThreadConstant.SLEEPTIME) + 1;
                Thread.sleep(value);
            }
            catch (InterruptedException e) {
                log.error("thread error:" + e);
            }
        }
    }
}
