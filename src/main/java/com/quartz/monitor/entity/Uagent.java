package com.quartz.monitor.entity;

@SuppressWarnings("serial")
public class Uagent extends BaseDO {

    private int id;
    private String uainfo;
    private String ip;
    private String extend;
    private String createTime;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUainfo() {
        return uainfo;
    }
    public void setUainfo(String uainfo) {
        this.uainfo = uainfo;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getExtend() {
        return extend;
    }
    public void setExtend(String extend) {
        this.extend = extend;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
