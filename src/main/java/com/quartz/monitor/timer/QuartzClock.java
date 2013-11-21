package com.quartz.monitor.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 计数定时任务
 * @author
 *
 */
public class QuartzClock  {

    private final static Logger LOG = LoggerFactory.getLogger(QuartzClock.class);
    
    @Autowired
    private GetAuthorInfoTimer getAuthorInfoTimer;
    @Autowired
    private GetCatalogContentTimer getCatalogContentTimer;
    @Autowired
    private GetCatalogListTimer getCatalogListTimer;
    @Autowired
    private  GetContentInfoTimer getContentInfoTimer;
    @Autowired
    private GetChapterListTimer getChapterListTimer;
    @Autowired
    private GetChannelsTimer getChannelsTimer;
    @Autowired
    private GetHotRecommendTimer getHotRecommendTimer;
//    @Autowired
//    private GetRankConfTimer getRankConfTimer;
    @Autowired
    private GetRankTimer getRankTimer;
    @Autowired
    private GetRankTypeTimer getRankTypeTimer;
//    @Autowired
//    private GetRecommendConfTimer getRecommendConfTimer;
    @Autowired
    private GetRecommendTimer getRecommendTimer;
    @Autowired
    private GetRecommendTypeTimer getRecommendTypeTimer;
    @Autowired
    private IdentifyCnetTimer identifyCnetTimer;
    
    /**
     * 执行接口任务
     */
    protected void executeCommonJob() {
        LOG.info("start job every day task");
        LOG.info("======================start task1====================");
        getAuthorInfoTimer.executeAuthorInfoTask();
        getCatalogContentTimer.executeCatalogContentTask();
        getCatalogListTimer.executeCatalogListTask();
        getChannelsTimer.executeChannelsTask();
        getChapterListTimer.executeChapterListTask();
        getContentInfoTimer.executeContentInfoTask();
        getHotRecommendTimer.executeGetHotRecommendTask();
//        getRankConfTimer.executeRankConfTask();
        getRankTimer.executeRankTask();
        getRankTypeTimer.executeRankTypeTask();
//        getRecommendConfTimer.executeRecommendConfTask();
        getRecommendTimer.executeGetRecommendTask();
        getRecommendTypeTimer.executeGetRecommendTypeTask();
    }
    
    /**
     * 执行C网判断任务
     */
    protected void executeCnetJob() {
        identifyCnetTimer.executeIdentifyCnetTask();
    }

}
