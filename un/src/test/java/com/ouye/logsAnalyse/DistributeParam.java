package com.ouye.logsAnalyse;

import java.util.List;


public class DistributeParam {

	/**
	 * 待解析的地址集合
	 */
	private List<DistributeAddress> addresses;
	
	/**
	 * 坐标类型
	 */
	private String type;

	public List<DistributeAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<DistributeAddress> addresses) {
		this.addresses = addresses;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
