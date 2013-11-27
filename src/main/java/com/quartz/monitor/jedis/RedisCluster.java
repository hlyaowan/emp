/*
 * 
 */
// Created on 2013-7-25

package com.quartz.monitor.jedis;

import java.util.List;

/**
 * 单个redis集群配置
 * 
 * @author joe.chen
 */
public class RedisCluster {

    private List<RedisServer> serverList;

    /**
     * @return the serverList
     */
    public List<RedisServer> getServerList() {
        return serverList;
    }

    /**
     * @param serverList the serverList to set
     */
    public void setServerList(List<RedisServer> serverList) {
        this.serverList = serverList;
    }

}
