package com.ouye.logsAnalyse;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class ReadLogFiles {
	
	static String basefileurl="F:\\document\\产品\\statistics.log[20161122-20161204]";
	
	static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	static SimpleDateFormat sdftime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static List<APIFendanEntity> readLogs(String bdate,int dates,String userkey) {
		
		List<APIFendanEntity> apis=new ArrayList<APIFendanEntity>();
		
		String fileurls="statistics.log.#date#";
		
		Calendar calendar=Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(bdate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		List<String> datelist=new ArrayList<String>();
		for(int i=0;i<dates;i++){
			datelist.add(sdf.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		System.out.println(datelist);
		
		try {
			for(int i=0;i<datelist.size();i++){
				System.out.println(datelist.get(i));
				File file=new File(basefileurl+File.separator+fileurls.replaceAll("#date#", datelist.get(i)));
				List<String> lineslist = Files.readLines(file, Charsets.UTF_8);
				
				//单个文件
				for(int j=0;j<lineslist.size();j=j+11){
					APIFendanEntity fendanapi=new APIFendanEntity();
					String keyvalues[]=lineslist.get(j+1).split("=");
					if(keyvalues[0].equals("key")){
						if(keyvalues[1].equals(userkey)){
							fendanapi.setUserid(keyvalues[1]);
						}else{
							continue;
						}
					}
					
					String eidvalues[]=lineslist.get(j+2).split("=");
					fendanapi.setEid(eidvalues[1]);
					
					String deptidvalues[]=lineslist.get(j+3).split("=");
					fendanapi.setDeptid(deptidvalues[1]);
					
					String dcodevalues[]=lineslist.get(j+4).split("=");
					fendanapi.setDcode(dcodevalues[1]);
					
					
					String paramvalues[]=lineslist.get(j+5).split("=");
					
					//解析参数
					DistributeParam dp=null;
					List<DistributeAddress> addressparam =null;
					try {
						dp= JSON.parseObject(paramvalues[1], DistributeParam.class);
						addressparam = dp==null?null:dp.getAddresses();
					} catch (Exception e) {
						continue;
					}
					
					String resultvalues[]=lineslist.get(j+6).split("=");
					JSONArray jsonarray=null;
					boolean flag=true;
					try {
						jsonarray=JSON.parseObject(resultvalues[1]).getJSONObject("result").getJSONArray("results");
					} catch (Exception e) {
						flag=false;
						System.out.println(i+","+j);
					}
					
					String compltetimes[]=lineslist.get(j+7).split("=");
					fendanapi.setFendanTime(sdftime.parse(compltetimes[1]));
					
					if(addressparam!=null&&addressparam.size()>0){
						for(int k=0;k<addressparam.size();k++){
							APIFendanEntity fendans=cloneOne(fendanapi);
							fendans.setAddress(addressparam.get(k).getAddress());
							fendans.setOrderid(addressparam.get(k).getId());
							if(!flag){
								fendans.setResulttype("其他");
								fendans.setSmx(BigDecimal.ZERO);
								fendans.setSmy(BigDecimal.ZERO);
								fendans.setAreaid(null);
							}else{
								JSONObject jsonresult=jsonarray.getJSONObject(k);
								fendans.setResulttype(getResultTypeDesc(jsonresult.getString("resultType")));
								fendans.setSmx(jsonresult.getBigDecimal("x"));
								fendans.setSmy(jsonresult.getBigDecimal("y"));
								fendans.setAreaid(jsonresult.getString("areaName"));
							}
							
							if(!(fendans.getResulttype().equals("解析成功"))){
								apis.add(fendans);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apis;
	}
	
	
	
	public static void main(String[] args) {
		try {
			List<APIFendanEntity> apis=readLogs("2016-11-15",7,"8a04a77b4cbc865c014cc0b8dfc4001a");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getResultTypeDesc(String type){
		if(type==null||type.length()==0){
			return null;
		}
		
		//1（解析成功）；2（有坐标未定位到区）；3（未解析到坐标）；4（分单点与地址所在的区不一致）,5(地址不完整)
		if(type.equals("1")){
			return "解析成功";
		}
		else if(type.equals("2")){
			return "有坐标未定位到区";
		}
		else if(type.equals("3")){
			return "未解析到坐标";
		}
		else if(type.equals("4")){
			return "分单点与地址所在的区不一致";
		}
		else if(type.equals("5")){
			return "地址不完整";
		}else return "其他";
	}
	
	
	public static APIFendanEntity cloneOne(APIFendanEntity entity){
		APIFendanEntity record=new APIFendanEntity();
		record.setDcode(entity.getDcode());
		record.setDeptid(entity.getDeptid());
		record.setEid(entity.getEid());
		record.setFendanTime(entity.getFendanTime());
		record.setUserid(entity.getUserid());
		return record;
	}
	
	
}
