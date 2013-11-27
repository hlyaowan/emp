/*
 * 
 */
// Created on 2013-7-25

package com.quartz.monitor.jedis;


/**
 * 单台redis服务器配置
 * 
 * @author joe.chen
 */
public class RedisServer {

    private String host;

    private int    port;

    public RedisServer(){

    }

    public RedisServer(String host, int port){
        this.host = host;
        this.port = port;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

}
