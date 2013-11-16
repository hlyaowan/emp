package com.quartz.monitor.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quartz.monitor.dao.CatalogInfoDAO;
import com.quartz.monitor.entity.CatalogInfo;
import com.quartz.monitor.mybatis.MybatisTemplate;

@Repository
public class CatalogInfoDAOImpl extends MybatisTemplate implements CatalogInfoDAO {

    private static final String NAMESPACE = CatalogInfoDAO.class.getName().concat(".");


    public List<CatalogInfo> getCatalogInfoList(CatalogInfo condition) {
        return super.<CatalogInfo> getList(NAMESPACE.concat("CatalogInfoList"), condition);
    }
}
