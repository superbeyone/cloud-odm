package com.tdt.cloud.utils;

import com.tdt.cloud.utils.coordinate.GetMapIDFromLnglat;
import jcifs.smb.SmbFile;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TaskCordUtil
 * @description
 * @date 2019-03-26 15:57
 **/

public class TaskUtil {

    /**
     * 生成流水号
     *
     * @return 流水号字符串
     */
    public static String generateTaskSerialNum(int userId) {
        return generateSerialNum("T_", userId);
    }

    public static String setOperateResultMsg(String msg, String path) {
        StringBuilder builder = new StringBuilder();
        boolean msgIsNull = false;
        if (StringUtils.isBlank(msg)) {
            msgIsNull = true;
        }
        boolean pathIsNull = false;
        if (StringUtils.isBlank(path)) {
            pathIsNull = true;
        }
        if (msgIsNull && pathIsNull) {
            return null;
        }
        builder.append("{\"msg\":").append(msgIsNull ? "" : "\"").append(msg).append(msgIsNull ? "" : "\"")
                .append(",\"path\":").append(pathIsNull ? "" : "\"").append(path).append(pathIsNull ? "" : "\"").append("}");
        return builder.toString();
    }

    public static String generateCordSerialNum(int userId) {
        return generateSerialNum("C_", userId);
    }

    private static String generateSerialNum(String prefix, int userId) {
        return prefix + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + (userId >= 10 ? userId : ("0" + userId));
    }


    public static long calcExcelExportExecTime(File excelFile) {
        long sum = calcExcelExportExecTimeContainSkip(excelFile, 1);
        if (sum == -1) {
            return sum;
        } else {
            return calcExcelExportExecTimeContainSkip(excelFile, null);
        }
    }

   

    /**
     * 计算excel执行时间
     *
     * @param excelFile
     * @return 循环次数
     */
    private static long calcExcelExportExecTimeContainSkip(File excelFile, Integer limit) {

        Map<String, List<List<String>>> excelReadMap = new ConcurrentHashMap<>();
        excelReadMap = ExcelReadUtil.readExcel((File) excelFile, 1, limit);
        long sum = 0;
        if (excelReadMap.size() > 0) {
            for (Map.Entry<String, List<List<String>>> entry : excelReadMap.entrySet()) {


                for (List<String> col : entry.getValue()) {
                    if (col.size() != 9) {
                        return -1;
                    }
                    String type = col.get(2);
                    Double lonMax = getNumOfFloor(col.get(3));
                    if (lonMax == null || lonMax > 180) {
                        return -1;
                    }
                    Double latMax = getNumOfCeil(col.get(4));
                    if (latMax == null || latMax > 90) {
                        return -1;
                    }
                    Double lonMin = getNumOfFloor(col.get(5));
                    if (lonMin == null || lonMin < -180) {
                        return -1;
                    }
                    Double latMin = getNumOfFloor(col.get(6));
                    if (latMin == null || latMin < -90) {
                        return -1;
                    }
                    if (lonMin > lonMax || latMin > latMax) {
                        return -1;
                    }
                    if (isNotNum(col.get(7)) || isNotNum(col.get(8))) {
                        return -1;
                    }
                    try {
                        Integer levelMin = Integer.parseInt(col.get(7).split("\\.")[0]);
                        if (levelMin == null || levelMin < 1 || levelMin > 18) {
                            return -1;
                        }
                        Integer levelMax = Integer.parseInt(col.get(8).split("\\.")[0]);
                        if (levelMax == null || levelMax < 1 || levelMax > 18) {
                            return -1;
                        }
                        if (levelMin > levelMax) {
                            return -1;
                        }
                        int lonMinx = 0, latMinx = 0, lonMaxx = 0, latMaxx = 0;
                        for (int level = levelMin; level <= levelMax; level++) {

                            int[] minLatAndLon = GetMapIDFromLnglat.getMapID(lonMin, latMin, level, type);
                            if (minLatAndLon != null) {
                                lonMinx = minLatAndLon[0];
                                latMinx = minLatAndLon[1];
                            }
                            int[] maxLatAndLon = GetMapIDFromLnglat.getMapID(lonMax, latMax, level, type);
                            if (maxLatAndLon != null) {
                                lonMaxx = maxLatAndLon[0];
                                latMaxx = maxLatAndLon[1];
                            }
                            sum += (getTwoNumDiffer(lonMinx, lonMaxx) + 1) * (getTwoNumDiffer(latMinx, latMaxx) + 1);

                        }
                    } catch (Exception e) {
                        return -1;
                    }
                }

            }

        } else {
            return -1;
        }
        return sum;
    }


    /**
     * 获取当前文件夹大小
     *
     * @param directory
     * @return
     */
    public static long getFolderSize(File directory) {
        long length = 0;
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    length += file.length();
                } else {
                    length += getFolderSize(file);
                }
            }
        } else {
            return directory.length();
        }
        return length;
    }

    /**
     * 获取当前文件夹大小
     *
     * @param directory
     * @return
     */
    public static long getFolderSize(SmbFile directory) {
        long length = 0;
        try {
            if (directory.isDirectory()) {
                for (SmbFile file : directory.listFiles()) {
                    if (file.isFile()) {
                        length += file.length();
                    } else {
                        length += getFolderSize(file);
                    }
                }
            } else {
                return directory.length();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 向下取整
     *
     * @param num
     * @return
     */
    public static Double getNumOfFloor(String num) {
        String regex = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(num).matches()) {
            return null;
        }
        return Double.valueOf(num);
    }

    /**
     * 向上取整
     *
     * @param num
     * @return
     */
    public static Double getNumOfCeil(String num) {
        String regex = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(num).matches()) {
            return null;
        }
        return Double.valueOf(num);
    }

    public static boolean isNotNum(String num) {
        String regex = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(num).matches()) {
            return true;
        }
        return false;
    }

    private static int getTwoNumDiffer(int min, int max) {
        if (min == max) {
            return 1;
        } else {
            int diff = max - min;
            return diff > 0 ? diff + 2 : Math.abs(diff - 2);
        }
    }

    /**
     * 校验IP是否合法
     *
     * @param text
     * @return
     */
    public static boolean ipCheck(String text) {
        if (text != null && !text.isEmpty()) {
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // 判断ip地址是否与正则表达式匹配
            if (text.matches(regex)) {
                // 返回判断信息
                return true;
            } else {
                // 返回判断信息
                return false;
            }
        }
        return false;
    }


    public static void writeLog(StringBuilder sb, OutputStream logOutputStream) {
        try {
            if (sb.length() > 0) {
                logOutputStream.write(sb.toString().getBytes());
                logOutputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
