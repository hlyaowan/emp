package com.quartz.monitor.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;

import com.quartz.monitor.common.RedisConstant;
import com.quartz.monitor.common.ThreadConstant;
import com.quartz.monitor.jedis.JedisClient;


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
    @Autowired
    private GetRankTimer getRankTimer;
    @Autowired
    private GetRankTypeTimer getRankTypeTimer;
    @Autowired
    private GetRecommendTimer getRecommendTimer;
    @Autowired
    private GetRecommendTypeTimer getRecommendTypeTimer;
    @Autowired
    private IdentifyCnetTimer identifyCnetTimer;
    @Autowired
    private JedisClient jedisClient;

    /**
     * 执行作者信息
     */
    protected void executeAuthorInfoTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try { 
            //设置失效时间
            jedisClient.expireAt(shardedJedis);
            String author = jedisClient.getTimeCount(shardedJedis, RedisConstant.AUTHORINFO_KEY);
            int authorvalue = Integer.parseInt(author);
            if (authorvalue < ThreadConstant.AUTHORINFO_MAX_LIMITED) {
                getAuthorInfoTimer.executeAuthorInfoTask();
            }else{
                LOG.info("=====================getAuthorInfoTimer complete======================");
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeAuthorInfoTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
       
    }


    /**
     * 执行分栏信息
     */
    protected void executeCatalogContentTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String catalog = jedisClient.getTimeCount(shardedJedis, RedisConstant.CATALOGCONTENT_KEY);
            int catalogValue = Integer.parseInt(catalog);
            if(catalogValue<ThreadConstant.CATALOGCONTENT_MAX_LIMITED){
                getCatalogContentTimer.executeCatalogContentTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeCatalogContentTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /**
     * 执行分栏列表信息
     */
    protected void executeCatalogListTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String catalogListStr = jedisClient.getTimeCount(shardedJedis, RedisConstant.CATALOGLIST_KEY);
            int catalogValue = Integer.parseInt(catalogListStr);
            if(catalogValue<ThreadConstant.CATALOGLIST_MAX_LIMITED){
                getCatalogListTimer.executeCatalogListTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeCatalogListTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /**
     * 执行频道信息
     */
    protected void executeChannelsTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String channelStr = jedisClient.getTimeCount(shardedJedis, RedisConstant.CHANNELS_KEY);
            int channelValue = Integer.parseInt(channelStr);
            if(channelValue<ThreadConstant.CHANNELS_MAX_LIMITED){
                getChannelsTimer.executeChannelsTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeChannelsTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /**
     * 执行章节信息
     */
    protected void executeChapterListTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String chapterlStr = jedisClient.getTimeCount(shardedJedis, RedisConstant.CHAPTERLIST_KEY);
            int chapterValue = Integer.parseInt(chapterlStr);
            if(chapterValue<ThreadConstant.CHAPTERLIST_MAX_LIMITED){
                getChapterListTimer.executeChapterListTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeChapterListTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /**
     * 执行内容详情
     */
    protected void executeContentInfoTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String contentStr = jedisClient.getTimeCount(shardedJedis, RedisConstant.CONTENTINFO_KEY);
            int contentValue = Integer.parseInt(contentStr);
            if(contentValue<ThreadConstant.CONTENTINFO_MAX_LIMITED){
                getContentInfoTimer.executeContentInfoTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeContentInfoTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /**
     * 执行热门排行
     */
    protected void executeGetHotRecommendTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String hotStr = jedisClient.getTimeCount(shardedJedis, RedisConstant.HOTRECOMMEND_KEY);
            int hotValue = Integer.parseInt(hotStr);
            if(hotValue<ThreadConstant.HOTRECOMMEND_MAX_LIMITED){
                getHotRecommendTimer.executeGetHotRecommendTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeGetHotRecommendTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /**
     * 执行排行
     */
    protected void executeRankTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String rankStr = jedisClient.getTimeCount(shardedJedis, RedisConstant.RANK_KEY);
            int rankValue = Integer.parseInt(rankStr);
            if(rankValue<ThreadConstant.RANK_MAX_LIMITED){
                getRankTimer.executeRankTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeRankTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /**
     * 执行排行类型
     */
    protected void executeRankTypeTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String rankStr = jedisClient.getTimeCount(shardedJedis, RedisConstant.RANK_TYPE_KEY);
            int rankValue = Integer.parseInt(rankStr);
            if(rankValue<ThreadConstant.RANK_MAX_LIMITED){
                getRankTypeTimer.executeRankTypeTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeRankTypeTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /**
     * 执行推荐
     */
    protected void executeGetRecommendTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String recommendStr = jedisClient.getTimeCount(shardedJedis, RedisConstant.RECOMMEND_KEY);
            int recommendValue = Integer.parseInt(recommendStr);
            if(recommendValue<ThreadConstant.RECOMMEND_MAX_LIMITED){
                getRecommendTimer.executeGetRecommendTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeGetRecommendTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /**
     * 执行推荐任务
     */
    protected void executeGetRecommendTypeTask() {
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String recommendStr = jedisClient.getTimeCount(shardedJedis, RedisConstant.RECOMMENDTYPE_KEY);
            int recommendValue = Integer.parseInt(recommendStr);
            if(recommendValue<ThreadConstant.RECOMMENDTYPE_MAX_LIMITED){
                getRecommendTypeTimer.executeGetRecommendTypeTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeGetRecommendTypeTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }


    /**
     * 执行C网判断任务
     */
    protected void executeIdentifyCnetTask() {
        
        ShardedJedis  shardedJedis = jedisClient.getShardedJedis();
        try {
            String netStr = jedisClient.getTimeCount(shardedJedis, RedisConstant.IDENTIFYCNET_KEY);
            int netValue = Integer.parseInt( netStr);
            if(netValue<ThreadConstant.IDENTIFYCNET_MAX_LIMITED){
                identifyCnetTimer.executeIdentifyCnetTask();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            LOG.error("executeIdentifyCnetTask "+e.getMessage());
        }finally{
            if(shardedJedis!=null){
                jedisClient.repleaseClient(shardedJedis);
            }
        }
    }

}
