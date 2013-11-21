package com.quartz.monitor.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.quartz.monitor.entity.AppInfo;


public class ReadAppInfoUtil {

    @SuppressWarnings("unchecked")
    public  List<AppInfo> readAppInfoFile() {
        FileInputStream is = null;
        try {
            List<AppInfo> list = Collections.EMPTY_LIST;
            File file = new File(this.getClass().getResource("/").getPath() + "WEB-INF\\classes\\conf\\appinfo.xml");
            is = new FileInputStream(file);
            SAXBuilder sb = new SAXBuilder();
            Document doclisten = sb.build(is);
            List<Element> eleList = doclisten.getRootElement().getChild("appList").getChildren("appInfo");
            if (CollectionUtils.isNotEmpty(eleList)) {
                list = new ArrayList<AppInfo>();
            }
            for (int i = 0; i < eleList.size(); i++) {
                Element elemodel = (Element) eleList.get(i);
                AppInfo model = new AppInfo();
                model.setAppId(elemodel.getChildText("appid"));
                model.setAccessToken(elemodel.getChildText("accessToken"));
                list.add(model);
            }
            return list;
        }
        catch (Exception e) {
            return null;
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (Exception e) {
                }
            }
        }
    }


    public AppInfo getAppInfo(List<AppInfo> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            Random random = new Random();
            int randomvalue = random.nextInt(size);
            AppInfo appInfo = list.get(randomvalue);
            return appInfo;
        }
        return null;
    }
}
