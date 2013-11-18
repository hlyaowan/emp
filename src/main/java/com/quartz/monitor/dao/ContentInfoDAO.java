package com.quartz.monitor.dao;

import java.util.List;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.ContentInfo;

public interface ContentInfoDAO {
    public List<ContentInfo> getContentInfoList(AppInfo condition);
    
    public ContentInfo getContentInfo(ContentInfo condition) ;
}
