package com.ouye.service;

import java.util.List;
import java.util.Map;

public interface PointService {
	
	public List<Map<String,Object>> getDailyPointCounts(String eid);
	
	
	public List<Map<String,Object>> getDailyOrderCounts(String eid);
	
	public List<Map<String,Object>> getDailyFendanApiCounts(String eid);

}
