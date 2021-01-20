package com.tdt.cloud.utils.coordinate;


import org.apache.commons.lang3.StringUtils;

/**
 * @author huangkai
 * @describe
 * @time 20120419
 */
public class GetMapIDFromLnglat {

//	public static void main(String[] args) {
    //TDTPoint mapID = new TDTPoint();
    //GetMapIDFromLnglat gId = new GetMapIDFromLnglat();
    //gId.GetTDTMapIDFromLnglatUnderCenterProjection(118.212890625, 32.34765625, 12);
		/*mapID = gId.GetMapIDFromLngLatUnderWebMercatorProjection(114.932, 37.69, 12);
		System.out.println(mapID.x + " " + mapID.y + " " + mapID.z);
		mapID = gId.GetMapIDFromLnglatUnderCenterProjection(117.18566994531, 39.118195580077995, 18);
		System.out.println(mapID.x + " __" + mapID.y + " __" + mapID.z);*/

//	}

    /**
     * 经纬度转行列号
     *
     * @param lng   经度
     * @param lat   纬度
     * @param level 层级
     * @param type  投影类型
     * @return [0] 列号 ,[1] 行号, [2] 层级
     */
    public static int[] getMapID(double lng, double lat, int level, String type) {
        if (StringUtils.endsWithIgnoreCase(type, "c")) {
            return GetCerMapID(lng, lat, level);
        }
        if (StringUtils.endsWithIgnoreCase(type, "w")) {
            return GetMerMapID(lng, lat, level);
        }
        return null;
    }

    /**
     * 获取墨卡托投影的MapID
     *
     * @param lng
     * @param lat
     * @param lev
     * @return
     */
    public static int[] GetMerMapID(double lng, double lat, int lev) {

        if ((lat < -85.05112878) || (lat > 85.05112878) || (lng > 180) || (lng < -180) || lev < 1) {
            System.out.println("输入经纬度不在web墨卡托投影范围内！");
        } else {
            LngLatAndPixelInterconvertUnderMercatorProject lnglatPixelCoor = new LngLatAndPixelInterconvertUnderMercatorProject();
            TDTPoint lnglatPoint = new TDTPoint();
            lnglatPoint.x = lng;
            lnglatPoint.y = lat;
            lnglatPoint.z = lev;
            TDTPoint pixelCoordinatePoint = lnglatPixelCoor.GetMercatorProjectionPixelCoordinateFromLngLat(lnglatPoint);

            int ix = (int) (pixelCoordinatePoint.x / 256);
            int iy = (int) (pixelCoordinatePoint.y / 256);

            int[] MapID = new int[3];
            MapID[0] = ix;
            MapID[1] = iy;
            MapID[2] = lev;

            return MapID;
        }

        return null;
    }


    /**
     * 经纬直投下获取MapID
     *
     * @param lng
     * @param lat
     * @param lev
     * @return
     */
    public static int[] GetCerMapID(double lng, double lat, int lev) {

        if ((lat < -90) || (lat > 90) || (lng > 180) || (lng < -180) || lev < 1) {
            System.out.println("输入经纬度不符合中心投影要求！");
        } else {
            int n = (int) (Math.pow(2, (lev - 1)));
            double tempInterval = 180.0 / n;
            double mx = lng + 180.0;
            double my = lat + 90.0;
            mx /= tempInterval;
            my /= tempInterval;

            //ix,iy为瓦片编码
            int ix = (int) (mx);
            int iy = (int) (n - my);

            int[] MapID = new int[3];
            MapID[0] = ix;
            MapID[1] = iy;
            MapID[2] = lev;

            return MapID;
        }

        return null;
    }


}
    
