package com.quartz.monitor.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.quartz.monitor.entity.RankInfo;
import com.quartz.monitor.service.RankInfoService;

@SuppressWarnings("serial")
public class RankAction extends ActionSupport {
    private static Logger log = Logger.getLogger(RankAction.class);
    @Autowired
    private RankInfoService rankInfoService;
    
    private List<RankInfo> rankList;

    public List<RankInfo> getRankList() {
        return rankList;
    }

    public void setRankList(List<RankInfo> rankList) {
        this.rankList = rankList;
    }
    
    
    public String rankList(){
        rankList =rankInfoService.getRankInfoList(null);
        log.info("ranklist size:"+rankList.size());
        return "list";
    }
}
