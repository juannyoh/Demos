package com.test.http;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.entity.GpsEntity;
import com.util.JsonUtil;


public class toMQHttpClient {
	
	static String baseurl="http://192.168.10.251:8040/egispgw/terminal/sendgpsdata";
	 
	static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
      
    	//写mq
        GpsEntity Entity=new GpsEntity();
        Entity.setTerminalId("A000000");
        Entity.setX(104.068695);
        Entity.setY(30.5374131);
        Entity.setDatetime(sdf.format(new Date()));
        
        List<GpsEntity> list=new ArrayList<GpsEntity>();
        list.add(Entity);
        sendGps(list);
  
    }  
      
      
  
    public static String sendGps(List<GpsEntity> gpslist){  
        HttpClient httpclient = new DefaultHttpClient();  
        String smsUrl=baseurl;  
        HttpPost httppost = new HttpPost(smsUrl);  
        String strResult = "";  
          
        try {  
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
                
                JSONArray jsonArray = JSONArray.fromObject(gpslist);
                String arrays=JsonUtil.getStringFromJsonArray(jsonArray);
                JSONObject obj=new JSONObject();
                obj.put("gpsdatas", arrays);
                
                nameValuePairs.add(new BasicNameValuePair("param",obj.toString()));  
                httppost.addHeader("Content-type", "application/x-www-form-urlencoded");  
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));  
               
                System.out.println(httppost.toString());
                
                HttpResponse response = httpclient.execute(httppost);  
                if (response.getStatusLine().getStatusCode() == 200) {  
                    /*读返回数据*/  
                    String conResult = EntityUtils.toString(response.getEntity());  
                    strResult+="返回："+conResult;
                } else {  
                    String err = response.getStatusLine().getStatusCode()+"";  
                    strResult += "发送失败:"+err;  
                }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        return strResult;  
    }  
  
    
}
