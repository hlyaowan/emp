package com.quartz.monitor.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quartz.monitor.dao.VisitUserDAO;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.mybatis.MybatisTemplate;

/***
 * 
 * @author hlyaowan
 *
 */
@Repository
public class VisitUserDAOImpl  extends MybatisTemplate implements VisitUserDAO{

    private static final String NAMESPACE = VisitUserDAO.class.getName().concat(".");
    /***
     * 获取访问记录
     * @param condition
     * @return
     */
    public List<VisitUser> getVisitUserInfoList(VisitUser condition) {
        return super.<VisitUser> getList(NAMESPACE.concat("VisitUserList"), condition);
    }
    
    /***
     * 更新计数器
     * @param visit
     * @return
     */
    public int updateVisitUserNumber(VisitUser visit){
        return super.update(NAMESPACE.concat("updateVisitUser"), visit);
    }
}
