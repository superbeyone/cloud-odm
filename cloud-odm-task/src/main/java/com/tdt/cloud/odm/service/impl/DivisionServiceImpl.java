package com.tdt.cloud.odm.service.impl;

import com.tdt.cloud.odm.persist.DivisionMapper;
import com.tdt.cloud.odm.pojo.Division;
import com.tdt.cloud.odm.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className DivisionServiceImpl
 * @description
 * @date 2019-03-20 13:14
 **/
@Service
public class DivisionServiceImpl implements DivisionService {
    @Autowired
    DivisionMapper divisionMapper;

    /**
     * 获取所有行政区划信息
     *
     * @return
     */
    @Override
    public List<Division> getDivisionList() {
        return divisionMapper.getDivisionList();
    }

    /**
     * 根据Code获取行政区划名称
     *
     * @param provinceCode
     * @return
     */
    @Override
    public Division getDivisionByCode(String provinceCode) {
        return divisionMapper.getDivisionByCode(provinceCode);
    }
}
