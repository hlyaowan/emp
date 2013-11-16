package com.quartz.monitor.service;

import java.util.List;

import com.quartz.monitor.entity.RecommendInfo;


/**
 * 
 * @author hlyaowan
 *
 */
public interface RecommendInfoService {
    /***
     * 获取推荐内容
     * @param condition
     * @return
     */
    public List<RecommendInfo> getRecommendInfoList(RecommendInfo condition) ;
}
