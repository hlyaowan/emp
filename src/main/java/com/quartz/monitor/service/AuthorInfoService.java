package com.quartz.monitor.service;

import java.util.List;

import com.quartz.monitor.entity.AuthorInfo;

public interface AuthorInfoService {
    /***
     * 获取作者列表
     * @param condition
     * @return
     */
    public List<AuthorInfo> getAuthorInfoList(AuthorInfo condition);
}
