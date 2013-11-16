package com.quartz.monitor.interfaces;

public interface RankTypeService {

    public String getRankType(String appId, String accessToken);

    public String getRank(String appId, String accessToken, String contentType, String rankType, String rankTime,
                          int start, int count);

    public String getRankConf(String appId, String accessToken, String contentType, String catalogId,
                              String channelType, String rankType, String rankTime, int start, int count);
}
