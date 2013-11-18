package com.quartz.monitor.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.dao.CatalogInfoDAO;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.CatalogInfo;
import com.quartz.monitor.service.CatalogInfoService;

@Service
public class CatalogInfoServiceImpl implements CatalogInfoService{

    @Autowired
    private CatalogInfoDAO catalogDAO;
    public List<CatalogInfo> getCatalogInfoList(CatalogInfo condition) {
        return catalogDAO.getCatalogInfoList(condition);
    }
    
    /***
     * 获取分类实体
     * @param condition
     * @return
     */
    public CatalogInfo getCatalogInfo(CatalogInfo condition){
        return catalogDAO.getCatalogInfo(condition);
    }
}
