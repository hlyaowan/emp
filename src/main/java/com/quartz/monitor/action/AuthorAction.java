package com.quartz.monitor.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.quartz.monitor.entity.AuthorInfo;
import com.quartz.monitor.service.AuthorInfoService;

@SuppressWarnings("serial")
public class AuthorAction extends ActionSupport {

    private static Logger log = Logger.getLogger(AuthorAction.class);
    @Autowired
    private AuthorInfoService authorInfoService;
    private List<AuthorInfo> authorList;

    public List<AuthorInfo> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<AuthorInfo> authorList) {
        this.authorList = authorList;
    }
    
    
    public String authorList(){
        
        authorList =authorInfoService.getAuthorInfoList(null);
        log.info("list size:"+authorList.size());
        return "list";
    }
}
