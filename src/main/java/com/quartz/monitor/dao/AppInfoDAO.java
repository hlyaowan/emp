package com.quartz.monitor.dao;

import java.util.List;

import com.quartz.monitor.entity.AppInfo;


/***
 * @author hlyaowan
 */

public interface AppInfoDAO {
    /***
     * 获取app列表
     * @param condition
     * @return
     */
    public List<AppInfo> getAppInfoList(AppInfo condition);
    
    public AppInfo getAppInfo(AppInfo condition);
}
