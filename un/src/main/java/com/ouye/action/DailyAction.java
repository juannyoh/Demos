package com.ouye.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dituhui.service.IOrderStatisticService;
import com.ouye.service.PointService;
import com.ouye.service.UserService;

@Controller
@RequestMapping("daily")
public class DailyAction {

	@Resource
	private UserService userService;
	
	@Resource
	private PointService pointService;
	
	@Resource
	private IOrderStatisticService orderStatisticService;
	
	@RequestMapping("dailyusercounts")
	@ResponseBody
	public List<Map<String,Object>> getDailyUserCounts(){
		Map<String,Object> map=new HashMap<String,Object>();
		List<String> deptIdList=new ArrayList<String>();
		deptIdList.add("8a04a77b4cbc865c014cc0b8dfda001b");
		deptIdList.add("8a04a77b4e4949f0014e855f63df084d");
		List rlist=this.orderStatisticService.queryAllOrderGroupByReultType(deptIdList, null, null);
		System.out.println(rlist);
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
