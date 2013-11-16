package com.quartz.monitor.dao;

import java.util.List;

import com.quartz.monitor.entity.CatalogInfo;


/***
 * 
 * @author hlyaowan
 * 
 */
public interface CatalogInfoDAO {
    public List<CatalogInfo> getCatalogInfoList(CatalogInfo condition);
}
