package com.ouye.logsAnalyse;

import java.util.List;

public class DistributeXYParam {

	
	/**
	 * 待解析的地址集合
	 */
	private List<DistributePoint> points;
	
	/**
	 * 坐标类型
	 */
	private String type;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public List<DistributePoint> getPoints() {
		return points;
	}

	public void setPoints(List<DistributePoint> points) {
		this.points = points;
	}

	
	
}
