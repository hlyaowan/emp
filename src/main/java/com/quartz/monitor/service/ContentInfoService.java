package com.quartz.monitor.service;

import java.util.List;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.ContentInfo;


public interface ContentInfoService {
    public List<ContentInfo> getContentInfoList(AppInfo condition);
}
