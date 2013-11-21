package com.quartz.monitor.interfaces;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;


public class HttpRequest {
    public static Logger log = Logger.getLogger(HttpRequest.class);
    
    public static String sendGetRequest(String url,String appId,String accessToken) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpParams params=httpclient.getParams();  
        String str = null;
        try {
            HttpGet httpGet = new HttpGet(url);
//            httpclient.getParams().setIntParameter("http.socket.timeout", 2000);//设置请求服务器超时时间   
//            httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY); //不起作用
            HttpConnectionParams.setConnectionTimeout(params, 2000);  
            HttpConnectionParams.setSoTimeout(params, 1000); 
            httpGet.setHeader("app_id", appId);
            httpGet.setHeader("access_token", accessToken);
            httpGet.setHeader("format", "json");
            HttpResponse response = null;
            Long start = System.currentTimeMillis();
            response = httpclient.execute(httpGet);
            log.debug("response time:" + (System.currentTimeMillis() - start));
            
            InputStream inputS = response.getEntity().getContent();
            byte b[] = new byte[512000];
            byte a[] = new byte[1024];
            int readLen = -1;
            int total = 0;
            while ((readLen = inputS.read(a)) != -1) {
                System.arraycopy(a, 0, b, total, readLen);
                total += readLen;
            }
            str = new String(b, 0, total, "UTF-8");
        } catch (Exception e) {
//            e.printStackTrace();
        }
        httpclient.getConnectionManager().shutdown();
       return str;
    }
    
    public static void main(String[] args) {
        String url="http://api.189.cn/yd/read/rank/getRankType";
        String appid="214770620000030628";
        String accesstoken ="020ee759c474e29439233ed11cdf175a1384334447046";
        System.out.println(sendGetRequest(url,appid,accesstoken));
    }

}
