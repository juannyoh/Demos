package com.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public final class JsonUtil {
	/**
     * json����ת���ַ�
     * @param adata
     * @return
     * Description:
     * @Author Juanny oh
     * @Date 2016-2-19 ����10:50:03
     */
    public static String getStringFromJson(JSONObject adata) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("{");  
        for(Object key:adata.keySet()){  
            sb.append("\""+key+"\":\""+adata.get(key)+"\",");  
        }  
        String rtn = sb.toString().substring(0, sb.toString().length()-1)+"}";  
        return rtn;  
    }
    
    /**
     * json����ת���ַ�
     * @param adata
     * @return
     * Description:
     * @Author Juanny oh
     * @Date 2016-2-19 ����10:50:26
     */
    public static String getStringFromJsonArray(JSONArray adata) {  
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        Object[] objs= adata.toArray();
        if(objs!=null&&objs.length>0){
        	for(Object obj:objs){
        		JSONObject jobj=(JSONObject) obj;
            	sb.append(getStringFromJson(jobj));
            }
        }
        String rtn = sb.toString()+"]";  
        return rtn;  
    }
    

	/**
     * ��json�ַ��еõ���Ӧjava����
     * JSONArray�µ�toArray()������ʹ��
     * @param str
     * @return
     */
     public static Object[] getJsonToArray(String str) {
         JSONArray jsonArray = JSONArray.fromObject(str);
         return jsonArray.toArray();
     }
     
     /**
      * ��json�ַ�õ�json����
      * @param str
      * @return
      * Description:
      * @Author Juanny oh
      * @Date 2016-2-19 ����10:51:20
      */
     public static JSONObject getJsonObject(String str) {
    	 JSONObject jsonArray = JSONObject.fromObject(str);
         return jsonArray;
     }
}
