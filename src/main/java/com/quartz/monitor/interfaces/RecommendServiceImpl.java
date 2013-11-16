package com.quartz.monitor.interfaces;

import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

import com.quartz.monitor.common.RecommendConstant;


public class RecommendServiceImpl implements RecommendService {
    public static Logger log = Logger.getLogger(RecommendServiceImpl.class);
    /***
     * 获取推荐类型接口
     * @param appId
     * @param accessToken
     * @return
     */
    public String getRecommendType(String appId,String accessToken){
        return HttpRequest.sendGetRequest(RecommendConstant.GET_RECOMMEND_TYPE_URL, appId, accessToken);
    }
    /***
     * 获取推荐内容接口
     * @param appId
     * @param accessToken
     * @param recommendType
     * @param start
     * @param count
     * @return
     */
    public String getRecommend(String appId,String accessToken,String recommendType,int start,int count){
        String recommendUrl =buildRecommendUrl(recommendType, start, count);
        return HttpRequest.sendGetRequest(recommendUrl, appId, accessToken);
    }
    /***
     * 获取热门搜索推荐接口
     * @param appId
     * @param accessToken
     * @return
     */
    public String getHotRecommend(String appId,String accessToken){
        return HttpRequest.sendGetRequest(RecommendConstant.GET_HOTRECOMMEND_URL, appId, accessToken);
    }
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
    public String getRecommendConf(String appId,String accessToken,String contentType,String catalogId,String channelType,String timeType,String recType,int  start,int count){
        String recommendConfUrl  =buildRecommendConfUrl(contentType, catalogId, channelType, timeType, recType, start, count);
        return HttpRequest.sendGetRequest(recommendConfUrl, appId, accessToken);
    }
    
    public String buildRecommendUrl(String recommendType,int start,int count){
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(RecommendConstant.GET_RECOMMEND_URL);
            builder.setParameter("recommendType", recommendType);
            builder.setParameter("start", String.valueOf(start));
            builder.setParameter("count", String.valueOf(count));
            return builder.build().toString();
        } catch (Exception e) {
            log.error("build  buildRecommendUrl url error.", e);
        }
        return null;
    }
    
    public String buildRecommendConfUrl(String contentType,String catalogId,String channelType,String timeType,String recType,int  start,int count) {
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(RecommendConstant.GET_RECOMMENDCONF_URL);
            builder.setParameter("contentType", contentType);
            builder.setParameter("catalogId", catalogId);
            builder.setParameter("channelType", channelType);
            builder.setParameter("timeType", timeType);
            builder.setParameter("recType", recType);
            builder.setParameter("start", String.valueOf(start));
            builder.setParameter("count", String.valueOf(count));
            builder.setParameter("copyRight", String.valueOf(0));
            return builder.build().toString();
        } catch (Exception e) {
            log.error("build  buildRecommendUrl url error.", e);
        }
        return null;
    }
}
