package com.quartz.monitor.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.quartz.monitor.entity.CatalogInfo;
import com.quartz.monitor.service.CatalogInfoService;

@SuppressWarnings("serial")
public class CatalogAction extends ActionSupport {
    private static Logger log = Logger.getLogger(CatalogAction.class);
    private  List<CatalogInfo> catalogList = new ArrayList<CatalogInfo>();
    
    
    public List<CatalogInfo> getCatalogList() {
        return catalogList;
    }


    public void setCatalogList(List<CatalogInfo> catalogList) {
        this.catalogList = catalogList;
    }


    @Autowired
    private CatalogInfoService catalogInfoService;


   public String list() throws Exception {
       catalogList =catalogInfoService.getCatalogInfoList(null);
       log.info("getCatalogInfoList size:" + catalogList.size());
       return "list";
   }
}
