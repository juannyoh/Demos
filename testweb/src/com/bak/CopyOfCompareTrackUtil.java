/*package com.bak;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.supermap.data.GeoLine;
import com.supermap.data.Geometrist;
import com.supermap.data.Point2D;
import com.supermap.data.Point2Ds;
import com.supermap.egisptool.dao.CarDistributionOrderDAO;
import com.supermap.egisptool.dao.RepeatTraceDAO;
import com.supermap.egisptool.db.ReadWriteOracleBySpring;
import com.supermap.egisptool.entity.CarDistributionOrderEntity;
import com.supermap.egisptool.entity.RepeatTraceEntity;
import com.supermap.egisptool.gis.CalculateTrackOverlap;
import com.supermap.egisptool.gis.GetTrack;
import com.supermap.egisptool.util.StringUtil;
import com.supermap.egisptool.vo.GPSLine;
import com.supermap.egisptool.vo.GPSPoint;

*//**
 * 车辆GPS轨迹对比
 * 
 * @author liweigu
 * 
 *//*
@Component
public class CopyOfCompareTrackUtil {

	@Resource
	ReadWriteOracleBySpring readWriteOracleBySpring;
	
	@Resource
	RepeatTraceDAO repeatTraceDAO;
	
	@Resource
	CarDistributionOrderDAO carDistributionOrderDAO;
	
	private static double RATE_MIN=0.6;

	*//**
	 * 计算重合率并写入数据库
	 	*
	 	*2016-8-1下午3:07:50
		*@author OuyangXiujuan
	 *//*
	public void caculteAllRepeatTrace(){
		List<Map<String,Object>> orderlist=getAllCarDistributionOrders();
		if(orderlist!=null&&orderlist.size()>0){
			List<Map<String,Object>> geolinelist= getAllTracksByCarDistrbutionOrders(orderlist);
			if(geolinelist!=null&&geolinelist.size()>0){
				List<RepeatTraceEntity> repeattraceresult=compareTracksResult(geolinelist);
				//保存结果入库 
				if(repeattraceresult!=null&&repeattraceresult.size()>0){
					this.repeatTraceDAO.save(repeattraceresult);
				}
			}
		}
	}

	
	*//**
	 * 查询所有符合条件的配车单数据
	 	*@return
	 	*2016-7-29下午2:48:59
		*@author OuyangXiujuan
	 *//*
	public List<Map<String,Object>> getAllCarDistributionOrders() {
		*//**
		 * 只针对区配的车辆进行轨迹对比。 只需要计算加盟车（和加盟车下挂车）的配车单； 只计算配送时间有重叠的配车单对；
		 * 取计算时刻起、配车时间在三天内的配车单进行计算； 完全直配的配车单可以不计算（待定）； 距离中心3公里（可配置）范围内的轨迹点不计算；
		 * 晚上10点到第二天早上5点间的轨迹点不计算。 算法核心是对从同一个中心出发的车
		 *//*
		List<Map<String,Object>> result = null;
		StringBuilder strb = new StringBuilder();
		strb.append("select a.* from (")
		.append(" select co.*,st.plate_number truckid from CARDISTRIBUTION_ORDER_MID co ")
		.append(" left join SECONDARY_TRUCK_INFO st on co.plate_number=st.plate_number and st.delete_flag=0 and st.type in (2,3) ")
		.append(" where co.delete_flag=0 ")
		.append(" and to_number(sysdate-co.trucking_start_time)<=3)a ")
		.append(" where a.truckid is not null ");
		//执行
		result=readWriteOracleBySpring.query(strb.toString());
		return result;
	}
	
	*//**
	 * 根据订单获取轨迹信息
	 	*@param list
	 	*@return
	 	*2016-7-29下午4:09:19
		*@author OuyangXiujuan
	 *//*
	public List<Map<String,Object>> getAllTracksByCarDistrbutionOrders(List<Map<String,Object>> list){
		List<Map<String,Object>> resultmap=null;
		if(list!=null&&list.size()>0){
			resultmap=new ArrayList<Map<String,Object>>();
			for(int i=0,s=list.size();i<s;i++){
				Map<String,Object> m=(Map<String,Object>) list.get(i);
				String orderid=StringUtil.convertObjectToString(m.get("CAR_DISTRIBUTION_ORDER_ID"));
				String startdate=StringUtil.convertObjectToString(m.get("TRUCKING_START_TIME"));
				String enddate=StringUtil.convertObjectToString(m.get("TRUCKING_END_TIME"));
				String platenumber=StringUtil.convertObjectToString(m.get("PLATE_NUMBER"));
				String centercode=StringUtil.convertObjectToString(m.get("CENTER_CODE"));
				//查询轨迹
				HashMap<String, GPSLine> map=GetTrack.getTrack(platenumber, startdate, enddate);
				Map<String,Object> ordermap=new HashMap<String,Object>();
				ordermap=formatTracks(orderid,map);//转换成geoline
				ordermap.put("startdate", startdate);
				ordermap.put("centercode", centercode);
				resultmap.add(ordermap);
			}
		}
		return resultmap;
	}
	
	
	*//**
	 * 将所有的符合条件的轨迹进行对比分析，得到对比结果，将结果保存入库
	 	*@param trackList
	 	*@return
	 	*2016-7-29下午5:39:50
		*@author OuyangXiujuan
	 *//*
	public List<RepeatTraceEntity> compareTracksResult(List<Map<String,Object>> trackList){
		List<RepeatTraceEntity> result=null;
		if(trackList!=null&&trackList.size()>0){
			result=new ArrayList<RepeatTraceEntity>();
			for(int i=0,s=trackList.size();i<s;i++){
				Map<String,Object> mapA=trackList.get(i);
				String orderidA=StringUtil.convertObjectToString(mapA.get("orderid"));
				GeoLine lineA=(GeoLine) mapA.get("geoline");
				String startdateA=StringUtil.convertObjectToString(mapA.get("startdate")).substring(0,10);
				String centercodeA=StringUtil.convertObjectToString(mapA.get("centercode"));
				for(int j=0;j<s;j++){
					Map<String,Object> mapB=trackList.get(j);
					String orderidB=StringUtil.convertObjectToString(mapB.get("orderid"));
					GeoLine lineB=(GeoLine) mapB.get("geoline");
					String startdateB=StringUtil.convertObjectToString(mapB.get("startdate")).substring(0,10);
					String centercodeB=StringUtil.convertObjectToString(mapB.get("centercode"));
					if(startdateA.equals(startdateB)&&centercodeA.equals(centercodeB)&&!orderidA.equals(orderidB)){//先判断是否同一天的订单,同一个中心的订单，且不是同一个订单
						//计算重合率
						double rate=CalculateTrackOverlap.calculateTrackOverlapRate(lineA, lineB);
						//double rates=caculateRepeatRate(lineA, lineB);//尝试用这种方式计算重合率？
						//重合率超过一定值时，保存起来
						if(rate>RATE_MIN){
							RepeatTraceEntity repeat=getRepeatTraceByOrderIds(orderidA,orderidB,rate);
							result.add(repeat);
						}
					}
				}
			}
		}
		return result;
	}

	*//**
	 * 将轨迹转化成geoline对象
	 	*@param orderid
	 	*@param map
	 	*@return
	 	*2016-8-1下午2:20:44
		*@author OuyangXiujuan
	 *//*
	public Map<String,Object> formatTracks(String orderid,HashMap<String, GPSLine> map){
		Map<String,Object> result=null;
		if(map!=null){
			GeoLine line=new GeoLine();
			result=new HashMap<String,Object>();
			Point2Ds point2ds=new Point2Ds();
			Set<String> keys=map.keySet();
			Iterator<String> itkeys=keys.iterator();
			while(itkeys.hasNext()){
				GPSLine gpsline=map.get(itkeys.next());
				if(gpsline!=null&&gpsline.getGPSPoints()!=null&&gpsline.getGPSPoints().size()>0){
					List<GPSPoint> points=gpsline.getGPSPoints();
					for(GPSPoint point:points){
						Point2D point2D=new Point2D(point.getReviseX(), point.getReviseY());
						point2ds.add(point2D);
					}
				}
			}
			line.addPart(point2ds);
			result.put("orderid", orderid);
			result.put("geoline", line);
		}
		return result;
	}
	
	*//**
	 * 构建轨迹重合实体对象
	 	*@param orderidA
	 	*@param orderidB
	 	*@param rate
	 	*@return
	 	*2016-8-1下午2:19:10
		*@author OuyangXiujuan
	 *//*
	public RepeatTraceEntity getRepeatTraceByOrderIds(String orderidA,String orderidB,double rate){
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat(".##");  
		CarDistributionOrderEntity orderA=this.carDistributionOrderDAO.findByCarDistributionOrderIdAndDeleteFlag(orderidA, 0);
		CarDistributionOrderEntity orderB=this.carDistributionOrderDAO.findByCarDistributionOrderIdAndDeleteFlag(orderidB, 0);
		RepeatTraceEntity repeat=new RepeatTraceEntity();
		repeat.setAreaCode(orderA.getAreaCode());//随机取A订单的？
		repeat.setCenterCode(orderA.getCenterCode());//
		repeat.setCarDistributionOrderId1(orderA.getCarDistributionOrderId());
		repeat.setCarDistributionOrderId2(orderB.getCarDistributionOrderId());
		repeat.setDeleteFlag(0);
		repeat.setGmtCreate(new Date());
		repeat.setPlateNumber1(orderA.getPlateNumber());
		repeat.setPlateNumber2(orderB.getPlateNumber());
		repeat.setRepeatRatio(Double.parseDouble(df.format((rate*100))));
		repeat.setTruckingStartTime1(orderA.getTruckingStartTime());
		repeat.setTruckingStartTime2(orderB.getTruckingStartTime());
		return repeat;
	}
	
	*//**
	 * 计算两条线的重合率（尝试中~~）
	 	*@param lineA
	 	*@param lineB
	 	*@return
	 	*2016-8-1下午3:01:52
		*@author OuyangXiujuan
	 *//*
	public double caculateRepeatRate(GeoLine lineA,GeoLine lineB){
		double rate=0;
		if(Geometrist.hasCommonLine(lineA, lineB)){//有重合
			GeoLine repeatline=(GeoLine) Geometrist.intersect(lineA, lineB);//线对象相交结果
			if(repeatline!=null&&repeatline.getLength()>0){
				rate=repeatline.getLength()/lineA.getLength();
			}
		}
		return rate;
	}
}
*/