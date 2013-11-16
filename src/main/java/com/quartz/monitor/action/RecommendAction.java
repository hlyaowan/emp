package com.quartz.monitor.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.quartz.monitor.entity.RecommendInfo;
import com.quartz.monitor.service.RecommendInfoService;

@SuppressWarnings("serial")
public class RecommendAction extends ActionSupport{
    private static Logger log = Logger.getLogger(RecommendAction.class);
    @Autowired
    private RecommendInfoService recommendInfoService;
    private List<RecommendInfo> recommendList ;
    public List<RecommendInfo> getRecommendList() {
        return recommendList;
    }
    public void setRecommendList(List<RecommendInfo> recommendList) {
        this.recommendList = recommendList;
    }
    
    public String recommList(){
        
        recommendList =recommendInfoService.getRecommendInfoList(null);
        log.info("recommend size:"+recommendList.size());
        return "list";
    }
}
