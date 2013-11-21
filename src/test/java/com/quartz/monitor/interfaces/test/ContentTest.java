package com.quartz.monitor.interfaces.test;


import org.junit.Test;

import com.quartz.monitor.common.AppinfoConstant;
import com.quartz.monitor.interfaces.ContentSeriveImpl;

/***
 * @author hlyaowan
 */
public class ContentTest {

	ContentSeriveImpl service =new ContentSeriveImpl();
	@Test
	public void getChannels() {
		
		String channelUrl =service.getChannels(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN) ;
		System.out.print(channelUrl);
	}
	
	@Test
	public void getCatalogList(){
		String channelID ="4"; 
		String catalogList = service.getCatalogList(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN, channelID);
		System.out.print(catalogList);
	}
	
	@Test
	public void getCatalogContent(){
		String catalogID="1000002629495";
		int start =2;
		int count =10;
		String catalogcontent = service.getCatalogContent(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN, catalogID, start, count);
		System.out.print(catalogcontent);
	}
	
	@Test
	public void getContentInfo(){
		String contentID ="100000216923845"; 
		String catalogList = service.getContentInfo(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN, contentID);
		System.out.print(catalogList);
	}
	
	@Test
	public void getAuthorInfo(){
		String authorID="10050026946076";
		int start =1;
		int count =10;
		String catalogcontent = service.getAuthorInfo(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN, authorID, start, count);
		System.out.print(catalogcontent);
	}

	@Test
	public void getChapterList(){
		String contentId="100000215670713";
		int start =1;
		int count =10;
		String catalogcontent = service.getChapterList(AppinfoConstant.APPID, AppinfoConstant.ACCESSTOKEN,contentId,start, count);
		System.out.print(catalogcontent);
	}
}
