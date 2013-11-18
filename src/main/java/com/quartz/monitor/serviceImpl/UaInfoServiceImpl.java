package com.quartz.monitor.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.dao.UaInfoDAO;
import com.quartz.monitor.entity.Uagent;
import com.quartz.monitor.service.UaInfoService;


@Service
public class UaInfoServiceImpl implements UaInfoService {
    
    @Autowired
    private UaInfoDAO uaInfoDAO;
    /***
     * 获取UA信息
     * 
     * @param condition
     * @return
     */
    public List<Uagent> getUagentInfoList(Uagent condition){
        return uaInfoDAO.getUagentInfoList(condition);
    }


    /***
     * 获取UA信息实体
     * 
     * @param condition
     * @return
     */
    public Uagent getUagentInfo(Uagent condition){
        return uaInfoDAO.getUagentInfo(condition);
    }
}
