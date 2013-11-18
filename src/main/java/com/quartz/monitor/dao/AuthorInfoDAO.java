package com.quartz.monitor.dao;

import java.util.List;

import com.quartz.monitor.entity.AuthorInfo;

public interface AuthorInfoDAO {
    /***
     * 获取作者列表
     * @param condition
     * @return
     */
    public List<AuthorInfo> getAuthorInfoList(AuthorInfo condition);
    /**
     * 获取作者对象
     * @param condition
     * @return
     */
    public AuthorInfo getAuthorInfo(AuthorInfo condition) ;
}
