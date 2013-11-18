package com.quartz.monitor.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quartz.monitor.dao.UaInfoDAO;
import com.quartz.monitor.entity.Uagent;
import com.quartz.monitor.mybatis.MybatisTemplate;


@Repository
public class UaInfoDAOImpl extends MybatisTemplate implements UaInfoDAO {
    private static final String NAMESPACE = UaInfoDAO.class.getName().concat(".");


    /***
     * 获取UA信息
     * 
     * @param condition
     * @return
     */
    public List<Uagent> getUagentInfoList(Uagent condition) {
        return super.<Uagent> getList(NAMESPACE.concat("UagentList"), condition);
    }


    /***
     * 获取UA信息实体
     * 
     * @param condition
     * @return
     */
    public Uagent getUagentInfo(Uagent condition) {
        return super.<Uagent> get(NAMESPACE.concat("UagentOne"), condition);

    }
}
