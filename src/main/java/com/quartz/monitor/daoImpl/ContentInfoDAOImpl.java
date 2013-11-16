package com.quartz.monitor.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quartz.monitor.dao.ContentInfoDAO;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.ContentInfo;
import com.quartz.monitor.mybatis.MybatisTemplate;


@Repository
public class ContentInfoDAOImpl extends MybatisTemplate implements ContentInfoDAO {
    private static final String NAMESPACE = ContentInfoDAO.class.getName().concat(".");
    
    public List<ContentInfo> getContentInfoList(AppInfo condition) {
        return super.<ContentInfo> getList(NAMESPACE.concat("ContentInfoList"), condition);
    }
}
