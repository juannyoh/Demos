package com.iplocation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class IpLocation {
	public static String do_post(String url, List<NameValuePair> name_value_pair) throws IOException {
		String body = "{}";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpPost httpost = new HttpPost(url);
            httpost.setEntity(new UrlEncodedFormEntity(name_value_pair, "utf-8"));
            HttpResponse response = httpclient.execute(httpost);
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return body;
    }
    public static String do_get(String url) throws ClientProtocolException, IOException {
        String body = "{}";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
        	System.out.println(url);
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return body;
    }
    
    public static void main(String[] args) {
		/*try {
			String result=do_get("http://ditu.amap.com/service/pl/pl.json");
			String addressresult=null;
			if(result!=null&&!result.equals("")){
				JSONObject jsonArray = JSONObject.fromObject(result);
				if(jsonArray.get("lat")!=null&&jsonArray.get("lng")!=null){
					addressresult=do_get("http://ditu.amap.com/service/regeo?longitude="+jsonArray.get("lng")+"&latitude="+jsonArray.get("lat"));
					System.out.println(addressresult);
					if(addressresult!=null&&!addressresult.equals("")){
						JSONObject addressArray = JSONObject.fromObject(addressresult);
						JSONObject dataArray = JSONObject.fromObject(addressArray.get("data"));
						System.out.println(dataArray.get("desc")+":"+dataArray.get("pos"));
					}
				}
			}
			System.out.println(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	corr();
	}
    
    
    public static  void corr(){
    	try {
			//String result=do_get("http://e.dituhui.com/egispapi/1.0/cm/coor_conv?key=40288fd94add378d014add37a3e00000&param={'coords':'116.37998867550326,39.938632472629735','from':'GPS','to':'SMM'}");
			String result="11";
    		String addressresult=null;
    		
			if(result!=null&&!result.equals("")){
				//JSONObject jsonArray = JSONObject.fromObject(result);
				double lat=36.06;
				double lng=103.84;
				String from="GPS";
				String to="SMLL";
				//if(jsonArray.get("lat")!=null&&jsonArray.get("lng")!=null){
					addressresult=do_get("http://e.dituhui.com/egispapi/1.0/cm/coor_conv?key=40288fd94add378d014add37a3e00000&param={\"coords\":\""+lng+","+lat+"\",\"from\":\""+from+"\",\"to\":\""+to+"\"}");
					System.out.println(addressresult);
					if(addressresult!=null&&!addressresult.equals("")){
						JSONObject addressArray = JSONObject.fromObject(addressresult);
						System.out.println(addressArray);
						JSONObject dataArray = JSONObject.fromObject(addressArray.get("data"));
						System.out.println(dataArray.get("province")+"----"+dataArray.get("cross_list"));
					}
				//}
			}
			//System.out.println(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   /* public static void testpost(){
		double lat=36.06;
		double lng=103.84;
		String from="GPS";
		String to="SMLL";
		
		List<NameValuePair> name_value_pair=new ArrayList<NameValuePair>();
		NameValuePair name_value1=new NameValuePair();
		name_value1.setName("key");
		name_value1.setValue("40288fd94add378d014add37a3e00000");
		
		Map m=new HashMap();
		m.put("", value)
		
		NameValuePair name_value2=new NameValuePair();
		name_value2.setName("param");
		name_value2.setValue("40288fd94add378d014add37a3e00000");
		
		
		String result=do_post("http://e.dituhui.com/egispapi/1.0/cm/coor_conv",)
	}
	*/
	
}
