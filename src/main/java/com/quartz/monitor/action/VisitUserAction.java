package com.quartz.monitor.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.service.VisitUserService;

@SuppressWarnings("serial")
public class VisitUserAction extends ActionSupport {

    private static Logger log = Logger.getLogger(VisitUserAction.class);
    @Autowired
    private VisitUserService visitUserService;
    private List<VisitUser> visitList;
    public List<VisitUser> getVisitList() {
        return visitList;
    }
    public void setVisitList(List<VisitUser> visitList) {
        this.visitList = visitList;
    }
    
    public String visitList(){
        visitList =visitUserService.getVisitUserInfoList(null);
        
        log.info("visitlist size:"+visitList.size());
        return "list";
    }
}
