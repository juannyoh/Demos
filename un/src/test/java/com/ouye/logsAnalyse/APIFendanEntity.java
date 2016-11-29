package com.ouye.logsAnalyse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class APIFendanEntity implements Serializable {
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	private static final long serialVersionUID = 1L;
	private String address;
	private String userid;
	private String eid;
	private String deptid;
	private String dcode;
	private String admincode;
	private String province;
	private String city;
	private String county;
	private String areaid;
	private String resulttype;
	private Date fendanTime;
	private BigDecimal smx;
	private BigDecimal smy;
	
	private String orderid;
	
	
	
	
	
	
	public APIFendanEntity() {
	}

	public APIFendanEntity(String address, String userid, String eid,
			String deptid, String dcode, String admincode, String province,
			String city, String county, String areaid,String resulttype,
			Date fendanTime,BigDecimal smx,BigDecimal smy) {
		this.address = address;
		this.userid = userid;
		this.eid = eid;
		this.deptid = deptid;
		this.dcode = dcode;
		this.admincode = admincode;
		this.province = province;
		this.city = city;
		this.county = county;
		this.areaid = areaid;
		this.resulttype=resulttype;
		this.fendanTime=fendanTime;
		this.smx=smx;
		this.smy=smy;
	}

	public BigDecimal getSmx() {
		return smx;
	}

	public void setSmx(BigDecimal smx) {
		this.smx = smx;
	}

	public BigDecimal getSmy() {
		return smy;
	}

	public void setSmy(BigDecimal smy) {
		this.smy = smy;
	}

	public Date getFendanTime() {
		return fendanTime;
	}

	public void setFendanTime(Date fendanTime) {
		this.fendanTime = fendanTime;
	}

	public String getResulttype() {
		return resulttype;
	}

	public void setResulttype(String resulttype) {
		this.resulttype = resulttype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDcode() {
		return dcode;
	}

	public void setDcode(String dcode) {
		this.dcode = dcode;
	}

	public String getAdmincode() {
		return admincode;
	}

	public void setAdmincode(String admincode) {
		this.admincode = admincode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	
	
}
