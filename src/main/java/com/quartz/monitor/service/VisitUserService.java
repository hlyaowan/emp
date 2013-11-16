package com.quartz.monitor.service;

import java.util.List;

import com.quartz.monitor.entity.VisitUser;

public interface VisitUserService {
    /***
     * 获取访问记录
     * @param condition
     * @return
     */
    public List<VisitUser> getVisitUserInfoList(VisitUser condition);
    
    /***
     * 更新计数器
     * @param visit
     * @return
     */
    public int updateVisitUserNumber(VisitUser visit);
}
