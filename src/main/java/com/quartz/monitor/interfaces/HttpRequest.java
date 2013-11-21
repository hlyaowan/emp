package com.quartz.monitor.interfaces;

import org.apache.log4j.Logger;

import com.quartz.monitor.common.HttpInvokeRequest;
import com.quartz.monitor.common.HttpInvokeResponse;
import com.quartz.monitor.common.SyncHttpInvokeClient;


public class HttpRequest {
    public static Logger log = Logger.getLogger(HttpRequest.class);
    
    private static SyncHttpInvokeClient client = null;
    public static String sendGetRequest(String url,String appId,String accessToken) {
        client = new SyncHttpInvokeClient();
        HttpInvokeRequest request = new HttpInvokeRequest(url, "post");
        HttpInvokeResponse response = client.invoke(request);
        request.setConnTimeout(2000);
        request.setSoTimeout(1000);
        request.addHeader("app_id", appId);
        request.addHeader("access_token", accessToken);
        return response.getContent();
    }
    
    
    public static void main(String[] args) {
        String url="http://api.189.cn/yd/read/rank/getRankType";
        String appid="214770620000030628";
        String accesstoken ="020ee759c474e29439233ed11cdf175a1384334447046";
        long start =System.currentTimeMillis();
        System.out.println(sendGetRequest(url,appid,accesstoken));
        long end =System.currentTimeMillis();
        System.out.println((end-start));
    }

}
