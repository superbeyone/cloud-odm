package com.tdt.cloud.commons.result;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TdtStaticConst
 * @description 静态常量提取
 * @date 2019-03-29 10:48
 **/

public class TdtStaticConst {
    
    public static final BigDecimal SIDE_LEN_SIZE = BigDecimal.valueOf(15);

    /**
     * smb协议前缀
     */
    public static final String SMB_PREFIX = "smb://";

    /**
     * 使用正则查询
     */
    public static final boolean USE_REGEX = false;

    /**
     * 文件hash
     */
    public static final int FOLDER_HASH = 100;

    /**
     * Mongo连接的Session key
     */
    public static final String MONGO_CONN_SESSION_KEY = "mongoConn";

    public static final String USER_SESSION_KEY = "user";

    /**
     * 天地图用户使用的行政区划编码
     */
    public static final String TDT_PROVINCE_CODE = "00";
    /**
     * 超级管理员角色
     */
    public static final String ADMIN_ROLE = "1";

    /**
     * 正在运行中的任务组名称
     */
    public static final String RUNNING_TASK_CORD_NAME = "async";

    /**
     * FTP上传线程名前缀
     */
    public static final String FTP_UPLOAD_THREAD_NAME = "ftp_upload";

    /**
     * pbf 瓦片最大个数
     */
    public static final int MAX_TILE_SIZE = 16777216;

    /**
     * 矢量数据数据库 前缀
     */
    public static final String VECTOR_TILE_PREFIX = "vt_";


    public static final String OPT_TYPE_OF_IMPORT = "import";
    public static final String OPT_TYPE_OF_EXPORT = "export";
    public static final String OPT_TYPE_OF_DELETE = "delete";
    public static final String OPT_TYPE_OF_UPDATE = "update";
    public static final String OPT_TYPE_OF_CHECK = "check";
    public static final String OPT_TYPE_OF_MIGRATE = "migrate";

    public static List<String> VECTOR_COLLECTION_NAME_LIST =
            Arrays.asList("vt_w_1_10", "vt_w_11_15", "vt_w_16", "vt_w_17", "vt_w_18",
                    "vt_c_1_10", "vt_c_11_15", "vt_c_16", "vt_c_17", "vt_c_18");

    /**
     * 每秒导入，按b计算
     */
    public static final int PRE_SECOND_EXEC_COUNT_OF_IMPORT = 50000;
    /**
     * 每秒导出，按b计算
     */
    public static final int PRE_SECOND_EXEC_COUNT_OF_EXPORT = 30000;
    /**
     * 每秒删除，按b计算
     */
    public static final int PRE_SECOND_EXEC_COUNT_OF_DELETE = 38000;
    /**
     * 每秒更新，按b计算
     */
    public static final int PRE_SECOND_EXEC_COUNT_OF_UPDATE = 32000;
    /**
     * 每秒校验，按b计算
     */
    public static final int PRE_SECOND_EXEC_COUNT_OF_CHECK = 32;
    /**
     * 每秒数据迁移，按b计算
     */
    public static final int PRE_SECOND_EXEC_COUNT_OF_MIGRATE = 32;

    public static volatile boolean EXEC_RESULT_FLAG = false;

    public static volatile Map<String, Boolean> MAP_FLAG = new ConcurrentHashMap<>();

    /**
     * @param username
     * @param id
     * @param flag     false:没有手动停止，true：手动停止
     */
    public static void putMapFlag(String username, Integer id, Boolean flag) {
        MAP_FLAG.put(username + "_" + id, flag);
    }

    public static boolean getMapFlagByUsernameAndId(String username, int id) {
        return MAP_FLAG.getOrDefault(username + "_" + id, false);
    }

    public static boolean removeMapFlagByUsernameAndId(String username, int id) {
        return MAP_FLAG.remove(username + "_" + id);
    }


}
