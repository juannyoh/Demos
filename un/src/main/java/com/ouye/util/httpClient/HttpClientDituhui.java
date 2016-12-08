package com.ouye.util.httpClient;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientDituhui {

	private static Logger LOGGER=Logger.getLogger(HttpClientDituhui.class);
	
	private final static String dubbourl="http://192.168.15.77:8091/dubbohttp/dubboService/com.dituhui.service.IOrderStatisticService?version=2.5.3";
	
	/**
	 * 
	 * @param url
	 * @Author Juannyoh
	 * 2016-12-8下午2:23:20
	 */
	public static void TestDubboHttp(String url) {
		url=dubbourl;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost=new HttpPost(url);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
            nameValuePairs.add(new BasicNameValuePair("dubbo", "2.5.3"));
            httppost.addHeader("Content-type", "application/xml");  
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));  
            System.out.println(httppost.toString());
            HttpResponse response = httpclient.execute(httppost);  
			if (response.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 */
				String conResult = EntityUtils.toString(response.getEntity());
				System.out.println(conResult);
//				JSONArray jsonArray = JSONArray.fromObject(conResult);
			}else{
				System.out.println(response);
			}
		} catch (Exception e) {
			LOGGER.info("调用大众版网点接口失败："+e);
		}
	}
	
	
	/**
	 * 获取大众版用户地图
	 * @param url
	 * @return
	 * @Author Juannyoh
	 * 2016-10-14上午11:42:17
	 */
	public static HttpEntity getDituhuiUserMap(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget=new HttpGet(url);
		HttpEntity conResult =null;
		try {
			HttpResponse response = httpclient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 */
				conResult = response.getEntity();
			}
		} catch (Exception e) {
			LOGGER.info("调用大众版网点接口失败："+e);
		}
		return conResult;
	}
	
	public static void main(String[] args) {
		TestDubboHttp(null);
	}
}
