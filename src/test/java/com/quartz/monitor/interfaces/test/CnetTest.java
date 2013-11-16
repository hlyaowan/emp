package com.quartz.monitor.interfaces.test;

import org.junit.Test;

import com.quartz.monitor.common.AppinfoConstant;
import com.quartz.monitor.interfaces.CnetServiceImpl;

public class CnetTest {

	CnetServiceImpl cent =new CnetServiceImpl();
	@Test
	public void test() {
		String ua ="";
		String ip="";
		String timestamp="2013-11-14 17:23:32";
		String cnet =cent.identifyCnet(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN, ua, ip,timestamp);
		System.out.print(cnet);
	}

}
