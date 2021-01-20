package com.tdt.cloud.utils.coordinate;

import java.util.HashMap;
import java.util.Map;


/**
 * gis左边转换
 *
 * @author ack
 * @version 1.0
 */
public class CoordinateConverter {

    /**
     * 根据经纬度计算行列号
     */
    public static Map<String, int[]> calculateRankNumber(Coordinate coordinate) {
        if (null == coordinate) {
            return null;
        }
        Map<String, int[]> map = new HashMap<>();
        if ("c".equals(coordinate.getProjection())) {
            int[] leftBottom = GetMapIDFromLnglat.GetCerMapID(coordinate.getLeftBottomLongitude(),
                    coordinate.getLeftBottomLatitude(), coordinate.getLevel());
            int[] rightUp = GetMapIDFromLnglat.GetCerMapID(coordinate.getRightUpLongitude(),
                    coordinate.getRightUpLatitude(), coordinate.getLevel());
            map.put("leftBottom", leftBottom);
            map.put("rightUp", rightUp);
        }
        if ("w".equals(coordinate.getProjection())) {
            int[] leftBottom = GetMapIDFromLnglat.GetMerMapID(coordinate.getLeftBottomLongitude(),
                    coordinate.getLeftBottomLatitude(), coordinate.getLevel());
            int[] rightUp = GetMapIDFromLnglat.GetMerMapID(coordinate.getRightUpLongitude(),
                    coordinate.getRightUpLatitude(), coordinate.getLevel());
            map.put("leftBottom", leftBottom);
            map.put("rightUp", rightUp);
        }

        return map;
    }

    public static void main(String[] args) {
        Coordinate coordinate = new Coordinate();

        //{"lonlat":"116.68304,40.00763","level":3,"bound":"115.36469,39.75471,118.0014,40.25962","zoom":10,"layers":"vec,cva","projection":"w"}
//        double lonMin = 73.6591072348;
//        double latMin = 38.4931452303;
//        double lonMax = 74.8522791297;
//        double latMax = 40.2719764424;
        //左 101.81，28.01
        //右 117.74, 36.31

        //lat 纬度 lon 经度
//        double lonMin = 116.455078125;
//        double latMin = 25.42236328125;
//        double lonMax = 116.45782470703125;
//        double latMax = 25.42510986328125;


        double lonMin = 108.36;
        double latMin = 19.96;
        double lonMax = 108.37;
        double latMax = 19.97;
        int level = 16;

        String type = "c";

        coordinate.setLeftBottomLatitude(latMin);
        coordinate.setLeftBottomLongitude(lonMin);

        coordinate.setRightUpLatitude(latMax);
        coordinate.setRightUpLongitude(lonMax);

//        coordinate.setLeftBottomLatitude(lonMin);
//        coordinate.setLeftBottomLongitude(latMin);
//
//        coordinate.setRightUpLatitude(lonMax);
//        coordinate.setRightUpLongitude(latMax);
        coordinate.setLevel(level);
        coordinate.setProjection(type);
        Map<String, int[]> map = calculateRankNumber(coordinate);
        for (Map.Entry<String, int[]> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue()[0] + "\t" + entry.getValue()[1]);
        }

        System.out.println("-------------------------------");
        int[] ws = GetMapIDFromLnglat.getMapID(lonMax, latMax, level, type);
        System.out.println(ws[0] + "\t" + ws[1]);

    }



}
