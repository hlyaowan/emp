package com.quartz.monitor.dao;

import java.util.List;

import com.quartz.monitor.entity.RankInfo;

public interface RankInfoDAO {
    /***
     * 获取排行信息
     * @param condition
     * @return
     */
    public List<RankInfo> getRankInfoList(RankInfo condition) ;
}
