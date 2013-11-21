package com.quartz.monitor.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
       
//       List list= catalogInfoService.getCatalogInfoList(null);
//       StringBuffer sb =new StringBuffer();
//       sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//       sb.append("<response>");
//       sb.append("<catalogList>");
//       for (int i=0;i<catalogList.size();i++) {
//           Map map =(HashMap)list.get(i);
//           sb.append("<catalogInfo>");
//           sb.append("<channelId>"+map.get("channelId")+"</channelId>");
//           sb.append("<catalogId>"+map.get("catalogId")+"</catalogId>");
//           sb.append("<catalogName>"+map.get("catalogName")+"</catalogName>");
//           sb.append("</catalogInfo>");
//       }
//       sb.append("</catalogList>");
//       sb.append("</response>");
//       System.out.println(sb.toString());
       log.info("getCatalogInfoList size:" + catalogList.size());
       return "list";
   }
}
