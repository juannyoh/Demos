package com.ouye.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.ouye.bean.PointEntity;
import com.ouye.dao.PointDao;
import com.ouye.service.PointService;

@Service
public class PointServiceImpl implements PointService {
	
	@Resource
	private PointDao pointDao;

	@Override
	public List<Map<String, Object>> getDailyPointCounts(String eid) {
		List<Map<String,Object>> maplist=null;
		List result=this.pointDao.getDailyPointsCounts(eid);
		if(result!=null&&result.size()>0){
			maplist=new ArrayList<Map<String,Object>>();
			for(Object obj:result){
				Map<String,Object> map=new HashMap<String,Object>();
				Object[] counts=(Object[]) obj;
				map.put("days", counts[0]);
				map.put("counts", counts[1]);
				maplist.add(map);
			}
		}
		return maplist;
	}

	@Override
	public List<Map<String, Object>> getDailyOrderCounts(String eid) {
		List<Map<String,Object>> maplist=null;
		List result=this.pointDao.getDailyOrdersCounts(eid);
		if(result!=null&&result.size()>0){
			maplist=new ArrayList<Map<String,Object>>();
			for(Object obj:result){
				Map<String,Object> map=new HashMap<String,Object>();
				Object[] counts=(Object[]) obj;
				map.put("days", counts[0]);
				map.put("counts", counts[1]);
				maplist.add(map);
			}
		}
		return maplist;
	}

	@Override
	public List<Map<String, Object>> getDailyFendanApiCounts(String eid) {
		List<Map<String,Object>> maplist=null;
		List result=this.pointDao.getDailyFendanApiCounts(eid);
		if(result!=null&&result.size()>0){
			maplist=new ArrayList<Map<String,Object>>();
			for(Object obj:result){
				Map<String,Object> map=new HashMap<String,Object>();
				Object[] counts=(Object[]) obj;
				map.put("days", counts[0]);
				map.put("counts", counts[1]);
				maplist.add(map);
			}
		}
		return maplist;
	}
	
//	public Page<PointEntity> findAll(PointEntity request) {
//	    Specification<PointEntity> specification = new Specifications<PointEntity>()
//	            .eq(StringUtils.isNotBlank(request.getName()), "name", request.getName())
//	            .eq(Objects.requireNonNull((Object)request.getAddress()), "age", request.getAddress())
//	            .build();
//	  
//	    return pointDao.findAll(specification, new PageRequest(0, 15));
//	}

}
