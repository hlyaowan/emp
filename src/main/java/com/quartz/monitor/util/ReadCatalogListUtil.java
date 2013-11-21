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

import com.quartz.monitor.entity.CatalogInfo;


public class ReadCatalogListUtil {
    @SuppressWarnings("unchecked")
    public List<CatalogInfo> readAppInfoFile() {
        FileInputStream is = null;
        try {
            List<CatalogInfo> list = Collections.EMPTY_LIST;
            File file = new File(this.getClass().getResource("/").getPath() + "WEB-INF\\classes\\conf\\cataloginfo.xml");
            is = new FileInputStream(file);
            SAXBuilder sb = new SAXBuilder();
            Document doclisten = sb.build(is);
            List<Element> eleList = doclisten.getRootElement().getChild("catalogList").getChildren("catalogInfo");
            if (CollectionUtils.isNotEmpty(eleList)) {
                list = new ArrayList<CatalogInfo>();
            }
            for (int i = 0; i < eleList.size(); i++) {
                Element elemodel = (Element) eleList.get(i);
                CatalogInfo model = new CatalogInfo();
                model.setCatalogId(elemodel.getChildText("channelId"));
                model.setCatalogName(elemodel.getChildText("catalogId"));
                model.setChannelId(elemodel.getChildText("catalogName"));
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
    public CatalogInfo getCatalogInfo(List<CatalogInfo> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            Random random = new Random();
            int randomvalue = random.nextInt(size);
            CatalogInfo catalogInfo = list.get(randomvalue);
            return catalogInfo;
        }
        return null;
    }
}
