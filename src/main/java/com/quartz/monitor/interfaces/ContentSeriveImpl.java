package com.quartz.monitor.interfaces;

import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

import com.quartz.monitor.common.ContentConstant;


public class ContentSeriveImpl implements ContentService{
    public static Logger log = Logger.getLogger(ContentSeriveImpl.class);
    /***
     * 获取频道信息接口
     * @param appId
     * @param accessToken
     * @return
     */
    public String getChannels(String appId,String accessToken){
        return HttpRequest.sendGetRequest(ContentConstant.GET_CHANNELS_URL, appId, accessToken);
    }
    /***
     * 获取频道下分栏接口
     * @param appId
     * @param accessToken
     * @param channelID
     * @return
     */
    public String getCatalogList(String appId,String accessToken,String channelID){
        String catalogListUrl =buildCatalogListUrl(channelID);
        return HttpRequest.sendGetRequest(catalogListUrl, appId, accessToken);
    }
    /***
     * 获取分栏内容接口
     * @param appId
     * @param accessToken
     * @param catalogID
     * @param start
     * @param count
     * @return
     */
    public String getCatalogContent(String appId,String accessToken,String catalogID,int start,int count){
        String catalogcontentUrl =buildCatalogContentUrl(catalogID, start, count);
        return HttpRequest.sendGetRequest(catalogcontentUrl, appId, accessToken);
    }
    /***
     * 获取内容详情接口
     * @param appId
     * @param accessToken
     * @param contentID
     * @return
     */
    public String getContentInfo(String appId,String accessToken,String contentID)
    {
        String contentUrl =buildContentInfoUrl(contentID);
        return HttpRequest.sendGetRequest(contentUrl, appId, accessToken); 
    }
    /***
     * 获取书项作家信息接口
     * @param appId
     * @param accessToken
     * @param authorID
     * @param start
     * @param count
     * @return
     */
    public String getAuthorInfo(String appId,String accessToken,String authorID,int start,int count){
        String authorUrl =buildAuthorInfoUrl(authorID, start, count);
        return HttpRequest.sendGetRequest(authorUrl, appId, accessToken);
    }
    /***
     * 获取内容章节列表
     * @param appId
     * @param accessToken
     * @param contentId
     * @param start
     * @param count
     * @return
     */
    public String getChapterList(String appId,String accessToken,String contentId,int start,int count){
        String chapterUrl =buildChapterInfoUrl(contentId, start, count);
        return HttpRequest.sendGetRequest(chapterUrl, appId, accessToken);
    }
    
    
    public String buildCatalogListUrl(String channelID){
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(ContentConstant.GET_CATALOGLIST_URL);
            builder.setParameter("channelID", channelID);
            return builder.build().toString();
        } catch (Exception e) {
            log.error("build  buildCatalogListUrl url error.", e);
        }
        return null;
    }
    
    public String buildCatalogContentUrl(String catalogID,int start,int count){
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(ContentConstant.GET_CATALOGCONTENT_URL);
            builder.setParameter("catalogID", catalogID);
            builder.setParameter("start", String.valueOf(start));
            builder.setParameter("count", String.valueOf(count));
            return builder.build().toString();
        } catch (Exception e) {
            log.error("build  buildCatalogContentUrl url error.", e);
        }
        return null;
    }
    public String buildContentInfoUrl(String contentID){
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(ContentConstant.GET_CONTENTINFO_URL);
            builder.setParameter("contentID", contentID);
            return builder.build().toString();
        } catch (Exception e) {
            log.error("build  buildContentInfoUrl url error.", e);
        }
        return null;
    }
    
    public String buildAuthorInfoUrl(String authorID,int start,int count){
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(ContentConstant.GET_AUTHORINFO_URL);
            builder.setParameter("authorID", authorID);
            builder.setParameter("start", String.valueOf(start));
            builder.setParameter("count", String.valueOf(count));
            return builder.build().toString();
        } catch (Exception e) {
            log.error("build  buildAuthorInfoUrl url error.", e);
        }
        return null;
    }
    
    public String buildChapterInfoUrl(String contentId,int start,int count){
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(ContentConstant.GET_CHAPTERLIST_URL);
            builder.setParameter("contentId", contentId);
            builder.setParameter("start", String.valueOf(start));
            builder.setParameter("count", String.valueOf(count));
            return builder.build().toString();
        } catch (Exception e) {
            log.error("build  buildChapterInfoUrl url error.", e);
        }
        return null;
    }
}
