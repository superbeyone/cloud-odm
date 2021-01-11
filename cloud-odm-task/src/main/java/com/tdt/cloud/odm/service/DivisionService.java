package com.tdt.cloud.odm.service;


import com.tdt.cloud.odm.pojo.Division;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className DivisionOperatorService
 * @description 行政区划操作Service接口
 * @date 2019-03-20 13:12
 **/

public interface DivisionService {
    /**
     * 获取所有行政区划信息
     *
     * @return
     */
    List<Division> getDivisionList();

    /**
     * 根据Code获取行政区划名称
     *
     * @param provinceCode
     * @return
     */
    Division getDivisionByCode(String provinceCode);

}
