package com.ouye.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ouye.dao.UserDao;
import com.ouye.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public List<Map<String, Object>> getDailyUserCounts() {
		List<Map<String,Object>> maplist=null;
		List result=this.userDao.getDailyUserCounts();
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

}
