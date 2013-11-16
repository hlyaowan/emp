package com.quartz.monitor.interfaces;


public interface ContentService {
    /***
     * 获取频道信息接口
     * @param appId
     * @param accessToken
     * @return
     */
    public String getChannels(String appId,String accessToken);
    /***
     * 获取频道下分栏接口
     * @param appId
     * @param accessToken
     * @param channelID
     * @return
     */
    public String getCatalogList(String appId,String accessToken,String channelID);
    /***
     * 获取分栏内容接口
     * @param appId
     * @param accessToken
     * @param catalogID
     * @param start
     * @param count
     * @return
     */
    public String getCatalogContent(String appId,String accessToken,String catalogID,int start,int count);
    /***
     * 获取内容详情接口
     * @param appId
     * @param accessToken
     * @param contentID
     * @return
     */
    public String getContentInfo(String appId,String accessToken,String contentID);
    /***
     * 获取书项作家信息接口
     * @param appId
     * @param accessToken
     * @param authorID
     * @param start
     * @param count
     * @return
     */
    public String getAuthorInfo(String appId,String accessToken,String authorID,int start,int count);
    /***
     * 获取内容章节列表
     * @param appId
     * @param accessToken
     * @param contentId
     * @param start
     * @param count
     * @return
     */
    public String getChapterList(String appId,String accessToken,String contentId,int start,int count);
}
