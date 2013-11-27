package com.quartz.monitor.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.monitor.common.RedisConstant;
import com.quartz.monitor.util.RedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedis;


public class JedisClient {

    private Logger logger = LoggerFactory.getLogger(JedisClient.class);

    private JedisManager redisManager;

    private int model_info_db = 3;


    public JedisManager getRedisManager() {
        return redisManager;
    }


    public void setRedisManager(JedisManager redisManager) {
        this.redisManager = redisManager;
    }


    public int getModel_info_db() {
        return model_info_db;
    }


    public void setModel_info_db(int model_info_db) {
        this.model_info_db = model_info_db;
    }

    public ShardedJedis getShardedJedis() {
        return redisManager.getShardedJedis();
    }


    public void repleaseClient(ShardedJedis client) {
        redisManager.repleaseClient(client);
    }
    
    /***
     * 获取计数器key值
     * @param client
     * @param key
     * @return
     */
    public String getTimeCount(ShardedJedis client,String key) {
        String valueString =client.get(key);
        if(valueString==null){
            return "0";
        }
        return valueString;
    }

    /***
     *  让计数器递增
     * @param client
     * @param key
     */
    public void incrTimeCount(ShardedJedis client, String key) {
        client.incr(key);
    }
    
    public void expireAt(ShardedJedis client){  
        /***
         * public final static String CHANNELS_KEY = "CHANNELSKEY";
            public final static String CATALOGLIST_KEY = "CATALOGLIST_KEY";
            public final static String CATALOGCONTENT_KEY = "CATALOGCONTENT_KEY";
            public final static String CONTENTINFO_KEY = "CONTENTINFO_KEY";
            public final static String CHAPTERLIST_KEY = "CHAPTERLIST_KEY";
            public final static String AUTHORINFO_KEY = "AUTHORINFO_KEY";
            public final static String RANK_KEY = "RANK_KEY";
            public final static String RANK_TYPE_KEY = "RANK_TYPE_KEY";
            public final static String RECOMMEND_KEY = "RECOMMEND_KEY";
            public final static String RECOMMENDTYPE_KEY = "RECOMMENDTYPE_KEY";
            public final static String HOTRECOMMEND_KEY = "HOTRECOMMEND_KEY";
            public final static String IDENTIFYCNET_KEY = "IDENTIFYCNET_KEY";
         */
        long time =Long.parseLong(RedisUtil.getNextDayStart(1));
        client.expireAt(RedisConstant.CHANNELS_KEY, time);  
        client.expireAt(RedisConstant.CATALOGLIST_KEY,  time);  
        client.expireAt(RedisConstant.CATALOGCONTENT_KEY, time);  
        client.expireAt(RedisConstant.CONTENTINFO_KEY,  time);  
        client.expireAt(RedisConstant.CHAPTERLIST_KEY,  time);  
        client.expireAt(RedisConstant.AUTHORINFO_KEY,  time);  
        client.expireAt(RedisConstant.RANK_KEY,  time);  
        client.expireAt(RedisConstant.RANK_TYPE_KEY,  time);  
        client.expireAt(RedisConstant.RECOMMEND_KEY,  time);  
        client.expireAt(RedisConstant.RECOMMENDTYPE_KEY,  time);  
        client.expireAt(RedisConstant.HOTRECOMMEND_KEY,  time);  
        client.expireAt(RedisConstant.IDENTIFYCNET_KEY,  time);  
    }  

    

    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.11.95");  
        Jedis jedis= pool.getResource();    
        System.out.println(jedis.get(RedisConstant.AUTHORINFO_KEY));
    }

    // /**
    // * Redis db: 0:ip data 1: adaptive data 2:company_model data
    // 3-4:model_info data
    // */
    //
    // public Jedis getIPClient(ShardedJedis client,String ip) {
    // try {
    // Jedis j = client.getShard(ip);
    // j.select(0);
    // return j;
    // } catch (Exception e) {
    // logger.error("select IP client error." + e.toString());
    // return null;
    // }
    // }
    //
    // public Jedis getModelInfoClient(ShardedJedis client,String modelinfo) {
    // try {
    // Jedis j =client.getShard(modelinfo);
    // j.select(3);
    // return j;
    // } catch (Exception e) {
    // logger.error("select ModelInfo client error." + e.toString());
    // return null;
    // }
    // }
    //
    // public Jedis getAdaptiveClient(ShardedJedis client,String adaptive) {
    // try {
    // Jedis j = client.getShard(adaptive);
    // j.select(1);
    // return j;
    // } catch (Exception e) {
    // logger.error("select Adaptive client error." + e.toString());
    // return null;
    // }
    // }
    //
    // public Jedis getCompanyModelClient(ShardedJedis client,String
    // companyModel) {
    // try {
    // Jedis j = client.getShard(companyModel);
    // j.select(2);
    // return j;
    // } catch (Exception e) {
    // logger.error("select CompanyModel client error." + e.toString());
    // return null;
    // }
    // }
    //
    // public Jedis getJedisClient(ShardedJedis client,String key,int storeNum)
    // {
    // try {
    // Jedis j = client.getShard(key);
    // j.select(storeNum);
    // return j;
    // } catch (Exception e) {
    // logger.error("select CompanyModel client error." + e.toString());
    // return null;
    // }
    // }

    
}
