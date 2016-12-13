package com.ouye.service;

import java.util.List;
import java.util.Map;

import com.ouye.bean.UserEntity;

public interface UserService {
	
	public List<Map<String,Object>> getDailyUserCounts();
	
	
	public void addUser(UserEntity user);

}
