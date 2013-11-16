package com.quartz.monitor.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.quartz.monitor.entity.ContentInfo;
import com.quartz.monitor.service.ContentInfoService;

@SuppressWarnings("serial")
public class ContentAction extends ActionSupport {


    public List<ContentInfo> contentList =new ArrayList<ContentInfo>();
    private static Logger log = Logger.getLogger(ContentAction.class);
    @Autowired
    private ContentInfoService contentInfoService;
    public String list(){
        contentList =contentInfoService.getContentInfoList(null);
        log.info("contentList size is "+contentList.size());
        
        return "list";
    }
}
