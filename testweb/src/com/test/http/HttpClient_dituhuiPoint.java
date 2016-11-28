package com.test.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.util.JsonUtil;


public class HttpClient_dituhuiPoint {
	
//		static String baseurl="http://c.dituhui.com/maps/30855/get_markers";
	static String baseurl="http://e.dituhui.com/egispapi/1.0/lm/distribute";
		  
	    /** 
	     * @param args 
	     */  
	    public static void main(String[] args) {  
	      
	       /* String coords="103.84,36.06";  
	        String from="GPS";  
	        String to="SMLL";  
	        String ret=sendSms(coords ,from,to);  
	        System.out.println(ret);  
	  
	        if(ret.indexOf("失败")<0)  
	        {  
	            System.out.println("成功发送");  
	        }  
	        else  
	        {  
	            System.out.println("失败发送");  
	        } */ 
	        String admincodeurl="http://192.168.10.251:8042/egispapi/1.0/cm/getAdminElements";
	        
	        sendSms(null,null,null);
//	        Object obj=getAdminElements(admincodeurl,"510000","2","40288e9f483f48e501483f48eb060000");
//	        getAdminElements(baseurl);
//	        System.out.println(obj);
	  
	    }  
	      
	      
	  
	    public static String sendSms(String uid,String title,String content){  
	        HttpClient httpclient = new DefaultHttpClient();  
	        String smsUrl=baseurl;  
	        HttpPost httppost = new HttpPost(smsUrl);  
	        String strResult = "";  
	        try {  
	                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	                JSONArray address=new JSONArray();
	                for(int i=0;i<800;i++){
	                	JSONObject jobj = new JSONObject();  
	 	                jobj.put("address", "四川省成都市锦江区大田坎街162蜀都花园");  
	 	                jobj.put("id", i+"");
	 	                address.add(jobj);
	                }
	               /* JSONObject jobj2 = new JSONObject();  
	                jobj2.put("address", "四川省成都市锦江区大田坎街162蜀都花园");  
	                jobj2.put("id", "222");*/
//	                address.add(jobj2);
	                
	                JSONObject jobj3 = new JSONObject();  
	                jobj3.put("addresses", address);
	                jobj3.put("type", "SMLL");
	                  
	                nameValuePairs.add(new BasicNameValuePair("key", "40288e9f483f48e501483f48eb060000"));
	                nameValuePairs.add(new BasicNameValuePair("param", jobj3.toString()));  
	                httppost.addHeader("Content-type", "application/x-www-form-urlencoded");  
	                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));  
	                System.out.println(httppost.toString());
	                HttpResponse response = httpclient.execute(httppost); 
	                if (response.getStatusLine().getStatusCode() == 200) {  
	                    /*读返回数据*/  
	                    String conResult = EntityUtils.toString(response.getEntity());  
	                    JSONObject sobj =getJsonObject(conResult);  
	                    String result = sobj.getString("result");  
	                    
	                    /*JSONObject sobj2 = getJsonObject(result);  
	                    String result2 = sobj2.getString("results"); 
	                    
	                    JSONObject sobj3 = getJsonObject(result2);  
	                    String result3 = sobj3.getString("coords"); 
	                    
	                    Object[] obj=getJsonToArray(result3);*/
	                    
	                    /*Object[] resultobj=getJsonObject(conResult).getJSONObject("result")
	                    			.getJSONObject("results").getJSONArray("coords").toArray();*/
	                    
	                   /* for(int i=0;i<resultobj.length;i++){
	                    	JSONObject jsonObjects =(JSONObject)resultobj[i];
	                    	System.out.println(jsonObjects.get("x")+"-------"+jsonObjects.get("y"));
	                    }*/
//	                    strResult=conResult;
	                    System.out.println(11);
	                } else {  
	                    String err = response.getStatusLine().getStatusCode()+"";  
	                    strResult += "发送失败:"+err;  
	                }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	          
	        return strResult;  
	    }  
	  
	    /**
	     * get方式中使用 
	     * @param adata
	     * @return
	     * @Author Juannyoh
	     * 2016-10-25上午11:03:12
	     */
	    private static String getStringFromJson(JSONObject adata) {  
	        StringBuffer sb = new StringBuffer();  
	        sb.append("{");  
	        for(Object key:adata.keySet()){  
	            sb.append("\""+key+"\":\""+adata.get(key)+"\",");  
	        }  
	        String rtn = sb.toString().substring(0, sb.toString().length()-1)+"}";  
	        return rtn;  
	    } 
	    
	    

		/**
	     * 从json数组中得到相应java数组
	     * JSONArray下的toArray()方法的使用
	     * @param str
	     * @return
	     */
	     public static Object[] getJsonToArray(String str) {
	         JSONArray jsonArray = JSONArray.fromObject(str);
	         return jsonArray.toArray();
	     }
	     
	     public static JSONObject getJsonObject(String str) {
	    	 JSONObject jsonArray = JSONObject.fromObject(str);
	         return jsonArray;
	     }
	     
	     
	     public static Object getAdminElements(String url){
	 		HttpClient httpclient = new DefaultHttpClient();
	 		String smsUrl = url;
	 		/*smsUrl=smsUrl.replaceAll("#admincode#", admincode);
	 		smsUrl=smsUrl.replaceAll("#levelstr#", levelstr);
	 		smsUrl=smsUrl.replaceAll("#key#", key);*/
	 		HttpPost httppost = new HttpPost(smsUrl);
	 		HttpGet httpget=new HttpGet(smsUrl);
	 		
	 		Object[] resultobj=null;
	 		try {
	 			/*List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	 			JSONObject obj = new JSONObject();
	 			obj.put("admincode", admincode);
	 			obj.put("levelstr", levelstr);

	 			nameValuePairs.add(new BasicNameValuePair("key", key));
	 			nameValuePairs.add(new BasicNameValuePair("param", obj.toString()));*/
	 			httppost.addHeader("Content-type","application/x-www-form-urlencoded");
//	 			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

//	 			HttpResponse response = httpclient.execute(httppost);
	 			HttpResponse response = httpclient.execute(httpget);
	 			if (response.getStatusLine().getStatusCode() == 200) {
	 				/* 读返回数据 */
	 				String conResult = EntityUtils.toString(response.getEntity());
	 				System.out.println(conResult);
	 				JSONArray jsonArray = JSONArray.fromObject(conResult);
	 				for(int i=0;i<jsonArray.size();i++){
	 					JSONObject jsonresult=jsonArray.getJSONObject(i);
	 					System.out.println(jsonresult);
	 				}
	 				/*JSONObject jsonresult = JsonUtil.getJsonObject(conResult);
	 				if(jsonresult!=null&&jsonresult.getBoolean("success")){
	 					resultobj = JsonUtil.getJsonObject(conResult).getJSONObject("result")
	 							.getJSONArray("results").toArray();
	 				}*/
	 			}
	 		} catch (Exception e) {
	 			System.out.println("调用地址解析API失败："+e);
	 		}
	 		return resultobj;
	 	}
}
