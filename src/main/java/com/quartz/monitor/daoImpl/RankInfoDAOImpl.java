package com.quartz.monitor.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quartz.monitor.dao.RankInfoDAO;
import com.quartz.monitor.entity.RankInfo;
import com.quartz.monitor.mybatis.MybatisTemplate;

/***
 * 
 * @author hlyaowan
 *
 */
@Repository
public class RankInfoDAOImpl extends MybatisTemplate implements RankInfoDAO {
    private static final String NAMESPACE = RankInfoDAO.class.getName().concat(".");

    /***
     * 获取排行信息
     * @param condition
     * @return
     */
    public List<RankInfo> getRankInfoList(RankInfo condition) {
        return super.<RankInfo> getList(NAMESPACE.concat("RankInfoList"), condition);
    }
}
