package com.quartz.monitor.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quartz.monitor.dao.RecommendInfoDAO;
import com.quartz.monitor.entity.RankInfo;
import com.quartz.monitor.entity.RecommendInfo;
import com.quartz.monitor.mybatis.MybatisTemplate;
/***
 * 
 * @author hlyaowan
 *
 */
@Repository
public class RecommendInfoDAOImpl extends MybatisTemplate implements RecommendInfoDAO {

    
    private static final String NAMESPACE = RecommendInfoDAO.class.getName().concat(".");
    /***
     * 获取推荐内容
     * @param condition
     * @return
     */
    public List<RecommendInfo> getRecommendInfoList(RecommendInfo condition) {
        return super.<RecommendInfo> getList(NAMESPACE.concat("RecommendInfoList"), condition);
    }
    
    /***
     * 获取推荐实体
     * @param condition
     * @return
     */
    public RecommendInfo getRecommendInfo(RecommendInfo condition) {
        return super.<RecommendInfo> get(NAMESPACE.concat("SelectRecommendInfoOne"), condition);

    }
}
