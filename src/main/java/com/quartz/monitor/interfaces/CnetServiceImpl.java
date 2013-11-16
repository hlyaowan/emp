package com.quartz.monitor.interfaces;

import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

import com.quartz.monitor.common.IdentifyCnetConstant;

public class CnetServiceImpl implements CnetService {

    public static Logger log = Logger.getLogger(CnetServiceImpl.class);

    /***
     * http://api.189.cn/v2/eRead/identifyCnet/identifyCnet?app_id=xxxxx&access_token=xxxxx&timestamp=yyyy-MM-dd HH:mm:ss&ua=HW-C7300/C7300C58B303SP02 ACS-NETFRONT/3.2 CTC/1.0
     * 依据阅读用户手机的ua或者ip信息，识别阅读用户访问网络类型（c网）
     */
    public String identifyCnet(String appId, String accessToken, String ua, String ip,String timestamp) {
        String cnetUrl = buildIdentifyCnet(ua, ip,timestamp);
        return HttpRequest.sendGetRequest(cnetUrl, appId, accessToken);
    }

    public String buildIdentifyCnet(String ua, String ip,String timestamp) {
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(IdentifyCnetConstant.IDENTIFY_CNET_URL);
            builder.setParameter("ua", ua);
            builder.setParameter("ip", ip);
            builder.setParameter("timestamp", timestamp);
            return builder.build().toString();
        } catch (Exception e) {
            log.error("build  buildIdentifyCnet url error.", e);
        }
        return null;
    }

}
