package com.quartz.monitor.interfaces.test;

import org.junit.Test;

import com.quartz.monitor.common.AppinfoConstant;
import com.quartz.monitor.interfaces.RecommendServiceImpl;

public class RecommendTest {

	RecommendServiceImpl  impl =new RecommendServiceImpl();
	
	@Test
	public void getRecommendType() {
		String recommendType = impl.getRecommendType(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN);
		System.out.print(recommendType);
		
	}
	
	@Test
	public void getRecommend(){
		String recommendType ="1";
		int start=1;
		int count =10;
		String recommendUrl =impl.getRecommend(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN, recommendType, start, count) ;
		System.out.print(recommendUrl);
	}

	@Test
	public void getHotRecommend(){
		String recommendUrl =impl.getHotRecommend(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN) ;
		System.out.print(recommendUrl);
	}
	
	@Test
	/***
	 * 无数据
	 */
	public void getRecommendConf(){
		String contentType ="1";
		String catalogId=null;
		String channelType="1";
		String timeType="2";
		String recType="4"; //推荐类型 1：火爆推荐; 2：专题推荐; 3：精品推荐; 4：新书推荐;5：作家推荐(x); 6：分类推荐; 7：分类内容推荐; 8：同类图书推荐; 9：关联推荐; 10：精彩内容推荐; 11：单本精品推荐; 12：首发推荐; 13：热词推荐(x); 14：同部书推荐; 15：出版推荐.
		int start=1;
		int count =10;
		String recommendUrl =impl.getRecommendConf(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN, contentType, catalogId, channelType, timeType, recType, start, count);
		System.out.print(recommendUrl);
	}

}
