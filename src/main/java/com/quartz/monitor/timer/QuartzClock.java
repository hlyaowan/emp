package com.quartz.monitor.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 计数定时任务
 * 
 * @author
 * 
 */
public class QuartzClock {

    private final static Logger LOG = LoggerFactory.getLogger(QuartzClock.class);

    @Autowired
    private GetAuthorInfoTimer getAuthorInfoTimer;
    @Autowired
    private GetCatalogContentTimer getCatalogContentTimer;
    @Autowired
    private GetCatalogListTimer getCatalogListTimer;
    @Autowired
    private GetContentInfoTimer getContentInfoTimer;
    @Autowired
    private GetChapterListTimer getChapterListTimer;
    @Autowired
    private GetChannelsTimer getChannelsTimer;
    @Autowired
    private GetHotRecommendTimer getHotRecommendTimer;
    // @Autowired
    // private GetRankConfTimer getRankConfTimer;
    @Autowired
    private GetRankTimer getRankTimer;
    @Autowired
    private GetRankTypeTimer getRankTypeTimer;
    // @Autowired
    // private GetRecommendConfTimer getRecommendConfTimer;
    @Autowired
    private GetRecommendTimer getRecommendTimer;
    @Autowired
    private GetRecommendTypeTimer getRecommendTypeTimer;
    @Autowired
    private IdentifyCnetTimer identifyCnetTimer;


    /**
     * 执行作者信息
     */
    protected void executeAuthorInfoTask() {
        getAuthorInfoTimer.executeAuthorInfoTask();
    }


    /**
     * 执行分栏信息
     */
    protected void executeCatalogContentTask() {
        getCatalogContentTimer.executeCatalogContentTask();
    }


    /**
     * 执行分栏列表信息
     */
    protected void executeCatalogListTask() {
        getCatalogListTimer.executeCatalogListTask();
    }


    /**
     * 执行频道信息
     */
    protected void executeChannelsTask() {
        getChannelsTimer.executeChannelsTask();
    }


    /**
     * 执行章节信息
     */
    protected void executeChapterListTask() {
        getChapterListTimer.executeChapterListTask();
    }


    /**
     * 执行内容详情
     */
    protected void executeContentInfoTask() {
        getContentInfoTimer.executeContentInfoTask();
    }


    /**
     * 执行热门排行
     */
    protected void executeGetHotRecommendTask() {
        getHotRecommendTimer.executeGetHotRecommendTask();
    }


    /**
     * 执行排行
     */
    protected void executeRankTask() {
        getRankTimer.executeRankTask();
    }


    /**
     * 执行排行类型
     */
    protected void executeRankTypeTask() {
        getRankTypeTimer.executeRankTypeTask();
    }


    /**
     * 执行推荐
     */
    protected void executeGetRecommendTask() {
        getRecommendTimer.executeGetRecommendTask();
    }


    /**
     * 执行推荐任务
     */
    protected void executeGetRecommendTypeTask() {
        getRecommendTypeTimer.executeGetRecommendTypeTask();
    }


    /**
     * 执行C网判断任务
     */
    protected void executeIdentifyCnetTask() {
        identifyCnetTimer.executeIdentifyCnetTask();
    }

}
