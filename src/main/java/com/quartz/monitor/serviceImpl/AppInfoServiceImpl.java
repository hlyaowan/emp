package com.quartz.monitor.serviceImpl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.quartz.monitor.dao.AppInfoDAO;
import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.service.AppInfoService;


/***
 * @author hlyaowan
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Autowired
     private AppInfoDAO appInfoDao;


    public List<AppInfo> getAppInfoList(AppInfo condition) {
        return appInfoDao.getAppInfoList(condition);
    }
    
    public AppInfo getAppInfo(AppInfo condition){
        return appInfoDao.getAppInfo(condition);
    }
}
