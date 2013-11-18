package com.quartz.monitor.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.dao.AuthorInfoDAO;
import com.quartz.monitor.entity.AuthorInfo;
import com.quartz.monitor.service.AuthorInfoService;


@Service
public class AuthorInfoServiceImpl implements AuthorInfoService{

    @Autowired
    private AuthorInfoDAO authorInfoDAO;
    
    /***
     * 获取作者列表
     * @param condition
     * @return
     */
    public List<AuthorInfo> getAuthorInfoList(AuthorInfo condition){
        
        return authorInfoDAO.getAuthorInfoList(condition);
    }
    /**
     * 获取作者对象
     * @param condition
     * @return
     */
    public AuthorInfo getAuthorInfo(AuthorInfo condition) {
        return authorInfoDAO.getAuthorInfo(condition);
    }
}
