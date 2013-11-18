package com.quartz.monitor.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.dao.ContentInfoDAO;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.ContentInfo;
import com.quartz.monitor.service.ContentInfoService;


@Service
public class ContentInfoServiceImpl implements ContentInfoService {

    @Autowired
    private ContentInfoDAO contentInfoDAO;


    public List<ContentInfo> getContentInfoList(AppInfo condition) {
        return contentInfoDAO.getContentInfoList(condition);
    }
    
    public ContentInfo getContentInfo(ContentInfo condition) {
        return contentInfoDAO.getContentInfo(condition);
    }
}
