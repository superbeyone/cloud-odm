package com.tdt.cloud.utils.range;

import com.alibaba.fastjson.JSONObject;
import com.tdt.cloud.odm.pojo.data.RangeSpaceModel;
import com.tdt.cloud.utils.coordinate.GetMapIDFromLnglat;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className CalcCountFromRangeModel
 * @description
 * @date 2019-05-24 17:49
 **/

public class CalcCountFromRangeModelUtil {
    public static long calcExecCount(String param, String dbName) {
        if (StringUtils.isBlank(param)) {
            return -1;
        }
        try {
            RangeSpaceModel rangeSpaceModel = JSONObject.parseObject(param, RangeSpaceModel.class);
            Double lonMinDouble = Math.min(rangeSpaceModel.getLonMin(), rangeSpaceModel.getLonMax());
            Double lonMaxDouble = Math.max(rangeSpaceModel.getLonMin(), rangeSpaceModel.getLonMax());
            Double latMinDouble = Math.min(rangeSpaceModel.getLatMin(), rangeSpaceModel.getLatMax());
            Double latMaxDouble = Math.max(rangeSpaceModel.getLatMin(), rangeSpaceModel.getLatMax());
            Set<Integer> levels = rangeSpaceModel.getLevels();
            if (lonMinDouble > lonMaxDouble || latMinDouble > latMaxDouble || levels == null || levels.size() == 0) {
                return -1;
            }
            long sum = 0;
            String type = StringUtils.isBlank(rangeSpaceModel.getLyr()) ? dbName : rangeSpaceModel.getLyr();
            int lonMin = 0, lonMax = 0, latMin = 0, latMax = 0;
            for (Integer level : levels) {
                int[] minLatAndLon = GetMapIDFromLnglat.getMapID(lonMinDouble, latMinDouble, level, type);
                if (minLatAndLon != null) {
                    lonMin = minLatAndLon[0];
                    latMin = minLatAndLon[1];
                }

                int[] maxLatAndLon = GetMapIDFromLnglat.getMapID(lonMaxDouble, latMaxDouble, level, type);
                if (maxLatAndLon != null) {
                    lonMax = maxLatAndLon[0];
                    latMax = maxLatAndLon[1];
                }
                sum += Math.abs((Math.max(latMax, latMin) - Math.min(latMax, latMin) + 1) * (Math.max(lonMax, lonMin) - Math.min(lonMax, lonMin) + 1));
            }
            return sum;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
