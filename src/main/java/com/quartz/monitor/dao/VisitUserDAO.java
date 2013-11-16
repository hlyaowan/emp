package com.quartz.monitor.dao;

import java.util.List;

import com.quartz.monitor.entity.VisitUser;

/***
 * 
 * @author hlyaowan
 *
 */
public interface VisitUserDAO {
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
