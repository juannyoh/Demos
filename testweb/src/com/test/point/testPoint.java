package com.test.point;

import java.text.DecimalFormat;

import com.supermap.data.Point2D;

public class testPoint {
	
	
	public static void main(String[] args) {
		Point2D point=new Point2D(112.1152145,86.22535654);
		Point2D point2=new Point2D(112.116554654,86.2257464);
		Point2D point3=new Point2D(112.116554656,86.2257465);
		Point2D point4=new Point2D(112.116554657,86.2257467);
		Point2D point5=new Point2D(112.116554658,86.2257467);
		Point2D[] point2Ds=new Point2D[5];
		point2Ds[0]=point;
		point2Ds[1]=point2;
		point2Ds[2]=point3;
		point2Ds[3]=point4;
		point2Ds[4]=point5;
		System.out.println(point2Ds.length);
		point2Ds=formatPoint(point2Ds);
		System.out.println(point2Ds.length);
		
	}
	
	private static Point2D[] formatPoint(Point2D[] point2Ds) {
		try {
			DecimalFormat format = new DecimalFormat("##########.##");
			format.setMaximumFractionDigits(2);
			for (Point2D point : point2Ds) {
				point.setX(Double.valueOf(format.format(point.getX())).doubleValue());
				point.setY(Double.valueOf(format.format(point.getY())).doubleValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return point2Ds;
	}
}
