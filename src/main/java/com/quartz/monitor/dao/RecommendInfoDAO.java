package com.quartz.monitor.dao;

import java.util.List;

import com.quartz.monitor.entity.RecommendInfo;

/***
 * 
 * @author hlyaowan
 *
 */
public interface RecommendInfoDAO {
    /***
     * 获取推荐内容
     * @param condition
     * @return
     */
    public List<RecommendInfo> getRecommendInfoList(RecommendInfo condition) ;
    /***
     * 获取推荐实体
     * @param condition
     * @return
     */
    public RecommendInfo getRecommendInfo(RecommendInfo condition);
}
