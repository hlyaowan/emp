package com.quartz.monitor.service;

import java.util.List;

import com.quartz.monitor.entity.Uagent;

public interface UaInfoService {
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
