package com.quartz.monitor.interfaces.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.quartz.monitor.common.AppinfoConstant;
import com.quartz.monitor.interfaces.RankTypeServiceImpl;

public class RankTypeTest {

    public static Logger log = Logger.getLogger(RankTypeTest.class);
    RankTypeServiceImpl impl = new RankTypeServiceImpl();
    @Test
    public void getRankType() {
        
        String jsonResult = impl.getRankType(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN);
        log.error(jsonResult);
        System.out.println(jsonResult);
    }
    
    public void getRank(){
        String contentType ="1";
        String  rankType="1";
        String rankTime ="2";
        int start =1;
        int count =10;
        String rankjsonString =impl.getRank(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN, contentType, rankType, rankTime, start, count);
        System.out.println(rankjsonString);
    }
    
    public void getRankConf(){
        try {
            String contentType ="1";
            String catalogId =null;
            String channelType ="1";
            String  rankType="1";
            String rankTime ="2";
            int start =1;
            int count =10;
            String jsonConfString =impl.getRankConf(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN,  contentType, catalogId, channelType, rankType, rankTime, start, count);
            System.out.println(jsonConfString);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

}
