package com.quartz.monitor.interfaces;

import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

import com.quartz.monitor.common.RankConstant;

/***
 * @author hlyaowan
 */
public class RankTypeServiceImpl implements RankTypeService {

    public static Logger log = Logger.getLogger(RankTypeServiceImpl.class);

    /**
     * 获取天翼阅读支持的排行类型，如：点击排行、搜索排行、收藏排行等。
     */
    public String getRankType(String appId, String accessToken) {
        return HttpRequest.sendGetRequest(RankConstant.GET_RANK_TYPE, appId, accessToken);
    }

    /***
     * 获取排行内容数据接口 此接口可根据排行榜类型获取排行内容数据，如：名称、作者、点击值、收藏值、人气值等信息。
     * 
     * @param appId
     * @param accessToken
     * @param contentType
     * @param rankType
     * @param rankTime
     * @param start
     * @param count
     * @return
     */
    public String getRank(String appId, String accessToken, String contentType, String rankType, String rankTime,
                          int start, int count) {
        String rankUrlString = buildRankURL(contentType, rankType, rankTime, start, count);
        return HttpRequest.sendGetRequest(rankUrlString, appId, accessToken);
    }
    /***
     * 自定义获取排行内容
     * @param appId
     * @param accessToken
     * @param contentType
     * @param catalogId
     * @param channelType
     * @param rankType
     * @param rankTime
     * @param start
     * @param count
     * @return
     */
    public String getRankConf(String appId, String accessToken, String contentType, String catalogId, String channelType,
                         String rankType,String rankTime, int start, int count) {
        String rankUrlString = buildRankURLConf(contentType, catalogId,channelType,rankType, rankTime, start, count);
        return HttpRequest.sendGetRequest(rankUrlString, appId, accessToken);
    }

    public String buildRankURL(String contentType, String rankType, String rankTime, int start, int count) {

        URIBuilder builder = null;
        try {
            builder = new URIBuilder(RankConstant.GET_RANK_CONTEND_URL);
            builder.setParameter("contentType", contentType);
            builder.setParameter("rankType", rankType);
            builder.setParameter("rankTime", rankTime);
            builder.setParameter("start", String.valueOf(start));
            builder.setParameter("count", String.valueOf(count));
            return builder.build().toString();
        } catch (Exception e) {
            log.error("build  buildRankURL url error.", e);
        }
        return null;
    }
    
    public String buildRankURLConf(String contentType,  String catalogId, String channelType,String rankType, String rankTime, int start, int count) {

        URIBuilder builder = null;
        try {
            builder = new URIBuilder(RankConstant.GET_RANK_CONF_URL);
            builder.setParameter("contentType", contentType);
            builder.setParameter("catalogId", catalogId);
            builder.setParameter("channelType", channelType);
            builder.setParameter("rankType", rankType);
            builder.setParameter("rankTime", rankTime);
            builder.setParameter("start", String.valueOf(start));
            builder.setParameter("count", String.valueOf(count));
            return builder.build().toString();
        } catch (Exception e) {
            log.error("build  buildRankURLConf url error.", e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        RankTypeServiceImpl impl =new RankTypeServiceImpl();
        System.out.println(impl.buildRankURLConf("1", "10000000043","1","1", "4", 1, 10));
    }
}
