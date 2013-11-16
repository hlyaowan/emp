package com.quartz.monitor.service;

import java.util.List;

import com.quartz.monitor.entity.AppInfo;


/***
 * @author hlyaowan
 */

public interface AppInfoService {
    public List<AppInfo> getAppInfoList(AppInfo condition);


    public AppInfo getAppInfo(AppInfo condition);
}
