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
        HttpInvokeRequest request = new HttpInvokeRequest(url, "get");
        request.setConnTimeout(3000);
        request.setSoTimeout(2000);
        request.addHeader("app_id", appId);
        request.addHeader("access_token", accessToken);
        HttpInvokeResponse response = client.invoke(request);
        log.info("response size:"+response.getStatusCode());
        return response.getContent();
    }
    
    
    public static void main(String[] args) {
        String url="http://api.189.cn/v2/eRead/identifyCnet/identifyCnet?ua=SEC-SCHX559+UP.BROWSER%2F4.1.26L&ip=117.45.138.187&timestamp=2013-11-23+11%3A05%3A08&app_id=214119820000033855&access_token=a1acef183b900a0c1d34a0eb25a801a41385097127033";
        String appid="214770620000030628";
        String accesstoken ="020ee759c474e29439233ed11cdf175a1384334447046";
        long start =System.currentTimeMillis();
        System.out.println(sendGetRequest(url,appid,accesstoken));
//        long end =System.currentTimeMillis();
//        System.out.println((end-start));
    }

}
