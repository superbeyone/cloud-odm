package com.tdt.cloud.utils.coordinate;

import java.util.ArrayList;
import java.util.List;


/**
 * Web墨卡托投影下，像素与经纬度的互转 85.05112878
 * @author huangkai
 * @time 20120309
 *
 */

public class LngLatAndPixelInterconvertUnderMercatorProject {

	static double pi = 3.1415926535897932384626433832795;

	static double DEG_TO_RAD  = pi/180;

	static double RAD_TO_DEG  = 180/pi;

	static List<Double> Bc;

	static List<Double> Cc;

	static List<Double> Zc;

	static List<Double> Ac;

	static {

		int c = 256;

		Bc = new ArrayList<Double>();

		Cc = new ArrayList<Double>();

		Zc = new ArrayList<Double>();

		Ac = new ArrayList<Double>();

		for (int i=0; i < 21; i++)

		{

			double e = c/2;

			Bc.add(c/360.0);

			Cc.add(c/(2 * pi));

			Zc.add(e);

			Ac.add((double) c);

			c *= 2;

		}

	}

	

	/*

	 * 标准化

	 */

	public static double minmax(double a, double b, double c)

	{

		a = Math.max(a, b);

		a = Math.min(a, c);

		return a;

	}

	

	

	

	/**

	 * web墨卡托投影下经纬度转像素

	 * @param lnglatPoint

	 * @return

	 */

	 public  TDTPoint GetMercatorProjectionPixelCoordinateFromLngLat(TDTPoint lnglatPoint){		

		TDTPoint pixelCoordinatePoint = new TDTPoint();

		

		int e = (int)(Zc.get((int)lnglatPoint.z) + lnglatPoint.x * Bc.get((int) lnglatPoint.z));

		double f = minmax(Math.sin(DEG_TO_RAD * lnglatPoint.y), -0.9999, 0.9999);

		

		int g = (int)(Zc.get((int)lnglatPoint.z) + 0.5*Math.log((1+f)/(1-f))*-Cc.get((int)lnglatPoint.z));

		

		pixelCoordinatePoint.x = e;

		pixelCoordinatePoint.y = g;

		pixelCoordinatePoint.z = lnglatPoint.z;

		

		return pixelCoordinatePoint;

	}

	 

		/**

		 * web墨卡托投影下经纬度转像素

		 * @param lnglatPoint

		 * @return

		 */

		 public  TDTPoint GetMercatorProjectionPixelCoordinateFromLngLat(double lng, double lat, int lev){		

			TDTPoint pixelCoordinatePoint = new TDTPoint();

			

			int e = (int)(Zc.get(lev) + lng * Bc.get(lev));

			double f = minmax(Math.sin(DEG_TO_RAD * lat), -0.9999, 0.9999);

			

			int g = (int)(Zc.get(lev) + 0.5*Math.log((1+f)/(1-f))*-Cc.get(lev));

			

			pixelCoordinatePoint.x = e;

			pixelCoordinatePoint.y = g;

			pixelCoordinatePoint.z = lev;

			

			return pixelCoordinatePoint;

		}

	

	

	 

	/**

	 * web墨卡托投影下,像素转经纬度

	 * @param pixelCoordinatePoint

	 * @return

	 */

	public  TDTPoint GetLngLatFromMercatorProjectionPixelCoordinate(TDTPoint pixelCoordinatePoint){

		TDTPoint lnglatPoint = new TDTPoint();

		

		double f = (pixelCoordinatePoint.x - Zc.get((int)pixelCoordinatePoint.z))/Bc.get((int)pixelCoordinatePoint.z);

		double g = (pixelCoordinatePoint.y - Zc.get((int)pixelCoordinatePoint.z))/-Cc.get((int)pixelCoordinatePoint.z);

		double h = RAD_TO_DEG * ( 2 * Math.atan(Math.exp(g)) - 0.5 * pi);

		

		lnglatPoint.x = f;

		lnglatPoint.y = h;

		lnglatPoint.z = pixelCoordinatePoint.z;

		

		return lnglatPoint;

	}

}






