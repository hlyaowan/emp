package com.quartz.monitor.service;

import java.util.List;

import com.quartz.monitor.entity.CatalogInfo;

/***
 * 
 * @author hlyaowan
 *
 */
public interface CatalogInfoService {
    public List<CatalogInfo> getCatalogInfoList(CatalogInfo condition) ;
    /***
     * 获取分类实体
     * @param condition
     * @return
     */
    public CatalogInfo getCatalogInfo(CatalogInfo condition);
}
