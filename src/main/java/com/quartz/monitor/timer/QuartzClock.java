package com.quartz.monitor.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 计数定时任务
 * @author
 *
 */
public class QuartzClock  {

    private final static Logger LOG = LoggerFactory.getLogger(QuartzClock.class);
    
    /**
     * 执行接口任务
     */
    protected void executeDayJob() {
        LOG.info("start job every day task");
        LOG.info("======================start task1====================");
        GetAuthorInfoTimer.executeAuthorInfoTask();
        GetCatalogContentTimer.executeCatalogContentTask();
        GetCatalogListTimer.executeCatalogListTask();
        GetChannelsTimer.executeChannelsTask();
        getChapterListTimer.executeChapterListTask();
        GetContentInfoTimer.executeContentInfoTask();
        GetHotRecommendTimer.executeGetHotRecommendTask();
        GetRankConfTimer.executeRankConfTask();
        GetRankTimer.executeRankTask();
        GetRankTypeTimer.executeRankTypeTask();
        GetRecommendConfTimer.executeRecommendConfTask();
        GetRecommendTimer.executeGetRecommendTask();
        GetRecommendTypeTimer.executeGetRecommendTask();
    }
    
    /**
     * 执行C网判断任务
     */
    protected void executeCnetJob() {
        IdentifyCnetTimer.executeIdentifyCnetTask();
    }

}
