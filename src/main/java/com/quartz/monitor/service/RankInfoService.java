package com.quartz.monitor.service;

import java.util.List;

import com.quartz.monitor.entity.RankInfo;

public interface RankInfoService {
    /***
     * 获取排行信息
     * @param condition
     * @return
     */
    public List<RankInfo> getRankInfoList(RankInfo condition) ;
}
