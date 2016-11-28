package com.test.readcsv;

import com.supermap.convert.impl.SuperMapCoordinateConvertImpl;
import com.supermap.entity.Point;

public class readCsvAndConvertPoint {

	public static void main(String[] args) {
		Point point=new Point(116.409728310105,39.913766122024);
		point=SuperMapCoordinateConvertImpl.smLL2MC(point);
		System.out.println(point.getLon()+","+point.getLat());
		point=SuperMapCoordinateConvertImpl.smMCToLatLon(point);
		System.out.println(point.getLon()+","+point.getLat());
	}
	
}
