package com.tdt.cloud.odm.persist;

import com.tdt.cloud.odm.pojo.Division;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className DivisionOperatorMapper
 * @description 行政区划操作Mapper接口
 * @date 2019-03-20 13:15
 **/
@Mapper
@Repository
public interface DivisionMapper {

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
    Division getDivisionByCode(@Param("provinceCode") String provinceCode);
}
