package com.tdt.cloud.utils.coordinate;

import org.apache.commons.lang3.StringUtils;

/**
 * 行列号反推经纬度 (Bound) 算法
 * 在经纬/纬度只返回一个的时候,默认的策略是将经度/纬度 相加除以2,也就是取中心点
 * <p>
 * 具体的算法,是参照 黄斌 提供的javascript版本的算法移植过来的
 *
 * @author zhengwei
 * @create 2018-01-08 10:09
 */
public class MapIDRevertLonLatUtil {

    private static class BoundBox {
        double LatTop;
        double LatDown;
        double LonLeft;
        double LonRight;

        BoundBox(double[] bound) {
            LatTop = bound[3];
            LatDown = bound[1];
            LonLeft = bound[0];
            LonRight = bound[2];
        }

//        @Override
//        public String toString() {
//            return "纬度,上:" + LatTop + "|纬度,下:" + LatDown +
//                    "|经度,左:" + LonLeft + "|经度,右:" + LonRight;
//        }
    }

    private static class BoundCoordinate {
        double x = -1;
        double y = -1;
        int level = -1;
    }

    private static class PixelCoordinate {
        int x = -1;
        int y = -1;
        int level = -1;
    }

    private static PixelCoordinate getLeftTopCoor(int x, int y, int level) {
        PixelCoordinate coor = new PixelCoordinate();
        coor.x = x * 256;
        coor.y = y * 256;
        coor.level = level;
        return coor;
    }

    private static PixelCoordinate getRightDown(int x, int y, int level) {
        PixelCoordinate coor = new PixelCoordinate();
        coor.x = (x * 256) + 256;
        coor.y = (y * 256) + 256;
        coor.level = level;
        return coor;
    }


    /**
     * 行列号转经纬度
     *
     * @param x          列号
     * @param y          行号
     * @param level      层级
     * @param projection 投影方式
     * @return 经纬度
     */
    private static BoundBox getTileBoundFromMapID(int x, int y, int level, String projection) {
        BoundBox boundBox;

        PixelCoordinate leftTopCoor = getLeftTopCoor(x, y, level);
        PixelCoordinate rightDownCoor = getRightDown(x, y, level);
        double[] result;
        if (StringUtils.endsWithIgnoreCase(projection, "c")) {
            BoundCoordinate leftTopBoundCoorCer = revertCer(leftTopCoor);
            BoundCoordinate rightDownBoundCoorCer = revertCer(rightDownCoor);
            result = getBound(leftTopBoundCoorCer, rightDownBoundCoorCer);
        } else if (StringUtils.endsWithIgnoreCase(projection, "w")) {

            BoundCoordinate leftTopBoundCoorMer = revertMer(leftTopCoor);
            BoundCoordinate rightDownBoundCoorMer = revertMer(rightDownCoor);
            double[] merX = wgs84ToMercator(leftTopBoundCoorMer.x, leftTopBoundCoorMer.y);
            double[] merY = wgs84ToMercator(rightDownBoundCoorMer.x, rightDownBoundCoorMer.y);
            result = getBound(merX, merY);
        } else {
            result = new double[4];
        }
        boundBox = new BoundBox(result);
        return boundBox;
    }


    private static BoundCoordinate revertCer(PixelCoordinate coor) {
        double n = Math.pow(2, (coor.level - 1));
        double tempInterval = 180.0 / n;
        double pixelPerDegree = 256 / tempInterval;
        BoundCoordinate lngLatCoor = new BoundCoordinate();
        lngLatCoor.x = (coor.x / pixelPerDegree) - 180;
        lngLatCoor.y = 90 - (coor.y / pixelPerDegree);
        lngLatCoor.level = coor.level;
        return lngLatCoor;
    }


    private static int CONSTANT_SIZE = 21;
    private static double WMTS_PI = Math.PI;
    private static double RAD_TO_DEG = 180.0 / WMTS_PI;
    private static int WMTS_c = 256;
    private static double[] WMTS_Ac = new double[CONSTANT_SIZE];
    private static double[] WMTS_Bc = new double[CONSTANT_SIZE];
    private static double[] WMTS_Cc = new double[CONSTANT_SIZE];
    private static double[] WMTS_Zc = new double[CONSTANT_SIZE];

    static {
        for (int i = 0; i < CONSTANT_SIZE; i++) {
            WMTS_Ac[i] = WMTS_c;
            WMTS_Bc[i] = (WMTS_c / 360.0);
            WMTS_Cc[i] = (WMTS_c / (2.0 * WMTS_PI));
            double e = WMTS_c / 2.0;
            WMTS_Zc[i] = e;
            WMTS_c = WMTS_c * 2;
        }
    }

    private static BoundCoordinate revertMer(PixelCoordinate coor) {
        BoundCoordinate lngLatCoor = new BoundCoordinate();

        double f = (coor.x - WMTS_Zc[coor.level]) / WMTS_Bc[coor.level];
        double g = (coor.y - WMTS_Zc[coor.level]) / -WMTS_Cc[coor.level];

        double h = RAD_TO_DEG * (2 * Math.atan(Math.exp(g)) - 0.5 * WMTS_PI);
        lngLatCoor.x = f;
        lngLatCoor.y = h;
        lngLatCoor.level = coor.level;
        return lngLatCoor;
    }

    private static double[] wgs84ToMercator(double lng, double lat) {
        // 根据 黄斌 提供的javascript版算法移植
        double[] lonLat;
        double x = lng * 20037508.34 / 180;
        double y = Math.log(Math.tan((90 + lat) * Math.PI / 360)) / (Math.PI / 180.0);
        y = y * 20037508.34 / 180;
        lonLat = mercatorToWGS84(x, y);
        return lonLat;
    }

    private static double[] getBound(BoundCoordinate leftTopBoundCoor, BoundCoordinate rightDownBoundCoor) {
        double[] bound = new double[4];
        bound[0] = leftTopBoundCoor.x;
        bound[1] = rightDownBoundCoor.y;
        bound[2] = rightDownBoundCoor.x;
        bound[3] = leftTopBoundCoor.y;
        return bound;
    }

    private static double[] getBound(double[] merX, double[] merY) {
        double[] bound = new double[4];
        bound[0] = merX[0];
        bound[1] = merY[1];
        bound[2] = merY[0];
        bound[3] = merX[1];
        return bound;
    }

    /**
     * @param x
     * @param y
     * @param level
     * @param projection
     * @return 数组的顺序为: 经度均值, 纬度均值
     */
    public static double[] convert(int x, int y, int level, String projection) {
        BoundBox boundBox = getTileBoundFromMapID(x, y, level, projection);
        double[] lonlat = new double[2];
        lonlat[0] = (boundBox.LonLeft + boundBox.LonRight) / 2.0;
        lonlat[1] = (boundBox.LatTop + boundBox.LatDown) / 2.0;
        //System.out.println(boundBox);
        return lonlat;
    }

    /**
     * @param x
     * @param y
     * @param level
     * @param projection
     * @return 数组的顺序为: 左侧经度,顶部纬度,右侧经度,底部纬度
     */
    public static double[] convertToBoundBox(int x, int y, int level, String projection) {
        BoundBox boundBox = getTileBoundFromMapID(x, y, level, projection);
        double[] lonlat = new double[4];
        //xMin
        lonlat[0] = boundBox.LonLeft; 
        //yMax
        lonlat[1] = boundBox.LatTop;
        //xMax
        lonlat[2] = boundBox.LonRight;
        //yMin
        lonlat[3] = boundBox.LatDown;
        return lonlat;
    }

    public static double[] convertToBoundBox2(int x, int y, int level, String projection) {
        BoundBox boundBox = getTileBoundFromMapID(x, y, level, projection);

        return null;
    }


    private static double[] mercatorToWGS84(double lng, double lat) {
        double x = (lng / 20037508.34) * 180.0;
        double y = (lat / 20037508.34) * 180.0;

        y = 180 / Math.PI * (2 * Math.atan(Math.exp(y * Math.PI / 180)) - Math.PI / 2);

        double[] lonLat = new double[2];
        lonLat[0] = x;
        lonLat[1] = y;
        return lonLat;
    }

    public static void main(String[] args) {
//        double[] ws = convert(24177, 13727, 15, "w");
//        for (double w : ws) {
//            System.out.println(w);
//        }
        BoundBox boundBox = null;
        boundBox = getTileBoundFromMapID(53856, 10192, 16, "c");
        
        //卫星中心
//        boundBox = getTileBoundFromMapID(52496, 12748, 16, "c");
//        boundBox = getTileBoundFromMapID(46100, 25085, 16, "w");
        
        
//         boundBox = getTileBoundFromMapID(216829, 107520, 18, "w");
//         boundBox = getTileBoundFromMapID(1507, 789, 11, "w");
        System.out.println("LonLeft:\t" + boundBox.LonLeft);
        System.out.println("LatDown:\t" + boundBox.LatDown);

        System.out.println("LonRight:\t" + boundBox.LonRight);
        System.out.println("LatTop:\t" + boundBox.LatTop);
        System.out.println("-----------------------");

        System.out.println("x:\t" + (boundBox.LonLeft + boundBox.LonRight) / 2);
        System.out.println("y:\t"+(boundBox.LatDown+boundBox.LatTop)/2);

    }
//   public static void main(String[] args) {
//        double[] boundBox = convertToBoundBox(1999417000, 705885410, 9, "c");
//        System.out.println("map.centerAndZoom(new T.LngLat("+ boundBox.LonLeft +", "+ boundBox.LatDown +"), 9);");
//        System.out.println("var bounds = new T.LngLatBounds(new T.LngLat("+ boundBox.LonLeft +", "+ boundBox.LatDown +")," +
//                "new T.LngLat("+ boundBox.LonRight +", "+ boundBox.LatTop +"));");
//        System.out.println(Arrays.toString(boundBox));
//
//        System.out.println(Arrays.toString(WMTS_Ac));
//        System.out.println(Arrays.toString(WMTS_Bc));
//        System.out.println(Arrays.toString(WMTS_Cc));
//        System.out.println(Arrays.toString(WMTS_Zc));
//        System.out.println(boundBox[0]);
//    }

}
