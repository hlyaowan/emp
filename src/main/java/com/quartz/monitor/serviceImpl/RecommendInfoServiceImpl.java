package com.quartz.monitor.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.dao.RecommendInfoDAO;
import com.quartz.monitor.entity.RecommendInfo;
import com.quartz.monitor.service.RecommendInfoService;

/***
 * 
 * @author hlyaowan
 *
 */
@Service
public class RecommendInfoServiceImpl implements RecommendInfoService{
    @Autowired
    private RecommendInfoDAO recommendInfoDAO;
    /***
     * 获取推荐内容
     * @param condition
     * @return
     */
    public List<RecommendInfo> getRecommendInfoList(RecommendInfo condition) {
        return recommendInfoDAO.getRecommendInfoList(condition);
    }
}
