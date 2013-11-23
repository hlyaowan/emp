package com.quartz.monitor.interfaces.test;

import org.junit.Test;

import com.quartz.monitor.common.AppinfoConstant;
import com.quartz.monitor.interfaces.CnetServiceImpl;
import com.quartz.monitor.util.DateUtil;

public class CnetTest {

	CnetServiceImpl cent =new CnetServiceImpl();
	@Test
	public void test() {
		String ua ="JUC(LINUX;U;2.2.1;ZH_CN;XT800;480*854;)";
		String ip="220.181.111.85";
		String timestamp=DateUtil.getCurrentTimestamp();
		String cnet =cent.identifyCnet(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN, ua, ip,timestamp);
		System.out.print(cnet);
	}

}
