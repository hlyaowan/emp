package com.quartz.monitor.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.monitor.dao.RankInfoDAO;
import com.quartz.monitor.entity.RankInfo;
import com.quartz.monitor.service.RankInfoService;


/***
 * 
 * @author hlyaowan
 *
 */
@Service
public class RankInfoServiceImpl implements RankInfoService {
    @Autowired
    private RankInfoDAO rankInfoDAO;
    /***
     * 获取排行信息
     * @param condition
     * @return
     */
    public List<RankInfo> getRankInfoList(RankInfo condition) {
        return rankInfoDAO.getRankInfoList(condition);
    }
    
    /***
     * 获取排行信息
     * @param condition
     * @return
     */
    public RankInfo getRankInfo(RankInfo condition){
        return rankInfoDAO.getRankInfo(condition);
    }
}
