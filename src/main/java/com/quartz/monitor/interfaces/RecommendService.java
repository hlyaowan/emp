package com.quartz.monitor.interfaces;


/***
 * @author hlyaowan
 */
public interface RecommendService {
    /***
     * 获取推荐类型接口
     * @param appId
     * @param accessToken
     * @return
     */
    public String getRecommendType(String appId,String accessToken);
    /***
     * 获取推荐内容接口
     * @param appId
     * @param accessToken
     * @param recommendType
     * @param start
     * @param count
     * @return
     */
    public String getRecommend(String appId,String accessToken,String recommendType,int start,int count);
    /***
     * 获取热门搜索推荐接口
     * @param appId
     * @param accessToken
     * @return
     */
    public String getHotRecommend(String appId,String accessToken);
    /***
     * 自定义获取推荐内容接口
     * @param appId
     * @param accessToken
     * @param contentType
     * @param catalogId
     * @param channelType
     * @param timeType
     * @param recType
     * @param start
     * @param count
     * @return
     */
    public String getRecommendConf(String appId,String accessToken,String contentType,String catalogId,String channelType,String timeType,String recType,int  start,int count);
}
