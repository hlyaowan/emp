package com.quartz.monitor.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quartz.monitor.dao.AppInfoDAO;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.mybatis.MybatisTemplate;


/***
 * @author hlyaowan
 */
@Repository
public class AppInfoDAOImpl extends MybatisTemplate implements AppInfoDAO {
    private static final String NAMESPACE = AppInfoDAO.class.getName().concat(".");


    public List<AppInfo> getAppInfoList(AppInfo condition) {
        return super.<AppInfo> getList(NAMESPACE.concat("AppInfoList"), condition);
    }

    public AppInfo getAppInfo(AppInfo condition) {
        return super.<AppInfo> get(NAMESPACE.concat("AppInfo"), condition);
        
    }
}
