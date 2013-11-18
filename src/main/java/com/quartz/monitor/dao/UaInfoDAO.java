package com.quartz.monitor.dao;

import java.util.List;

import com.quartz.monitor.entity.Uagent;

public interface UaInfoDAO {

    /***
     * 获取UA信息
     * 
     * @param condition
     * @return
     */
    public List<Uagent> getUagentInfoList(Uagent condition);


    /***
     * 获取UA信息实体
     * 
     * @param condition
     * @return
     */
    public Uagent getUagentInfo(Uagent condition);
}
