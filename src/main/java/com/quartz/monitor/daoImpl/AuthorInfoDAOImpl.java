package com.quartz.monitor.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quartz.monitor.dao.AuthorInfoDAO;
import com.quartz.monitor.entity.AuthorInfo;
import com.quartz.monitor.mybatis.MybatisTemplate;

/***
 * @author hlyaowan
 */
@Repository
public class AuthorInfoDAOImpl extends MybatisTemplate implements AuthorInfoDAO {
    private static final String NAMESPACE = AuthorInfoDAO.class.getName().concat(".");

    /***
     * 获取作者列表
     * @param condition
     * @return
     */
    public List<AuthorInfo> getAuthorInfoList(AuthorInfo condition) {
        return super.<AuthorInfo> getList(NAMESPACE.concat("AuthorInfoList"), condition);
    }
}
