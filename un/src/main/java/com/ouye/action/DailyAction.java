package com.ouye.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ouye.service.PointService;
import com.ouye.service.UserService;

@Controller
@RequestMapping("daily")
public class DailyAction {

	@Resource
	private UserService userService;
	
	@Resource
	private PointService pointService;
	
	@RequestMapping("dailyusercounts")
	@ResponseBody
	public List<Map<String,Object>> getDailyUserCounts(){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> list=this.userService.getDailyUserCounts();
		map.put("records", list);
		return list;
	}
	
	@RequestMapping("dailypointcounts")
	@ResponseBody
	public List<Map<String,Object>> getDailyPointCounts(String eid){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> list=this.pointService.getDailyPointCounts(eid);
		map.put("records", list);
		String test=JSON.toJSONString(map);
		System.out.println(test);
		return list;
	}
	
	@RequestMapping("dailyordercounts")
	@ResponseBody
	public List<Map<String,Object>> getDailyOrderCounts(String eid){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> list=this.pointService.getDailyOrderCounts(eid);
		map.put("records", list);
		return list;
	}
	
	@RequestMapping("dailyfendanapicounts")
	@ResponseBody
	public List<Map<String,Object>> getDailyFendanApiCounts(String eid){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> list=this.pointService.getDailyFendanApiCounts(eid);
		map.put("records", list);
		return list;
	}
}
