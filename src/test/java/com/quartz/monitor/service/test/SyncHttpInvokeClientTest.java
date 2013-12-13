/**   
 * @Title: SyncHttpInvokeClientTest.java 
 * @Package com.tyyd.pay.commons.test.http.client 
 * @Description: TODO
 * @author joe.chen chenjun296#163.com
 * @date 2013-3-19 下午9:04:05 
 * @version V1.0   
 */
package com.quartz.monitor.service.test;

import junit.framework.TestCase;

import com.quartz.monitor.common.HttpInvokeRequest;
import com.quartz.monitor.common.HttpInvokeResponse;
import com.quartz.monitor.common.SyncHttpInvokeClient;


/**
 * @ClassName: SyncHttpInvokeClientTest
 * @Description: TODO
 * @author joe.chen chenjun296#163.com
 * @date 2013-3-19 下午9:04:05
 * 
 */
public class SyncHttpInvokeClientTest extends TestCase {

    private SyncHttpInvokeClient client = null;


    public void testSyncInvoke() {
        HttpInvokeRequest request =
                new HttpInvokeRequest(
                    "https://oauth.api.189.cn/emp/oauth2/v2/access_token?grant_type=client_credentials&app_id=164170610000033988&app_secret=7468a14d4e47764bdfac2d45f0467a7b&state=abc",
                    "post");
        HttpInvokeResponse response = client.invoke(request);

        System.out.println(response.getStatusCode());
        System.out.println(response.getContent());
    }


    public void testSyncInvokeByQuery() {
        HttpInvokeRequest request = new HttpInvokeRequest("http://s.taobao.com/search", "get");
        request.addParam("q", "a");
        HttpInvokeResponse response = client.invoke(request);

        System.out.println(response.getStatusCode());
        System.out.println(response.getContent());
    }


    @Override
    protected void setUp() throws Exception {
        client = new SyncHttpInvokeClient();
    }

}
