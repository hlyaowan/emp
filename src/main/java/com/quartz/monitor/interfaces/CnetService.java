package com.quartz.monitor.interfaces;


public interface CnetService {
    /***
     * 依据阅读用户手机的ua或者ip信息，识别阅读用户访问网络类型（c网）
     * 阅读用户识别（面向深度合作应用）
     * @param appId
     * @param accessToken
     * @param ua
     * @param ip
     * @return
     */
    public String identifyCnet(String appId,String accessToken,String ua,String ip,String timestamp);
}
