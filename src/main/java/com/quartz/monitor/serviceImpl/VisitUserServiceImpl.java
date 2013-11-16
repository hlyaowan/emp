package com.quartz.monitor.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.dao.VisitUserDAO;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.service.VisitUserService;


/***
 * 
 * @author hlyaowan
 *
 */
@Service
public class VisitUserServiceImpl implements VisitUserService{
    @Autowired
    private VisitUserDAO visitUserDAO;
    
    /***
     * 获取访问记录
     * @param condition
     * @return
     */
    public List<VisitUser> getVisitUserInfoList(VisitUser condition){
        return visitUserDAO.getVisitUserInfoList(condition);
    }
    
    /***
     * 更新计数器
     * @param visit
     * @return
     */
    public int updateVisitUserNumber(VisitUser visit){
        return visitUserDAO.updateVisitUserNumber(visit);
    }
}
