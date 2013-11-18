package com.quartz.monitor.timer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.quartz.monitor.entity.AppInfo;
import com.quartz.monitor.entity.AuthorInfo;
import com.quartz.monitor.entity.VisitUser;
import com.quartz.monitor.interfaces.ContentSeriveImpl;
import com.quartz.monitor.service.AppInfoService;
import com.quartz.monitor.service.AuthorInfoService;
import com.quartz.monitor.service.VisitUserService;

@Service
public class GetAuthorInfoTimer{

    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VisitUserService visitUserService;
    @Autowired
    private AuthorInfoService authorInfoService;
    // 电信api接口
    private static ContentSeriveImpl contentService = new ContentSeriveImpl();
    private static Logger log = Logger.getLogger(GetAuthorInfoTimer.class);

    public void executeAuthorInfoTask() {
        log.info("start getAuthorTask ...");
        AppInfo appInfo = appInfoService.getAppInfo(null);
        if (appInfo != null) {
            AuthorInfo authorInfo = authorInfoService.getAuthorInfo(null);
            if (authorInfo != null) {
                String jsonResult =
                        contentService.getAuthorInfo(appInfo.appId, appInfo.accessToken, authorInfo.authorId,
                            authorInfo.start, authorInfo.count);
                if (StringUtils.isNotEmpty(jsonResult)) {
                    VisitUser user = new VisitUser();
                    user.mothodName = "getAuthorInfo";
                    visitUserService.updateVisitUserNumber(user);
                }
            }
        }
    }
}
