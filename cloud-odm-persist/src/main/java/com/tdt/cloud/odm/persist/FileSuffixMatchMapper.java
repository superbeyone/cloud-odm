package com.tdt.cloud.odm.persist;

import com.tdt.cloud.odm.pojo.FileSuffixMatch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className FileSuffixMatchMapper
 * @description
 * @date 2019-05-14 15:30
 **/
@Mapper
@Repository
public interface FileSuffixMatchMapper {
    /**
     * 添加可操作文件后缀
     *
     * @param name
     * @return
     */
    boolean addOperateFileSuffix(@Param("name") String name);

    /**
     * 获取可操作文件后缀列表
     *
     * @return
     */
    List<FileSuffixMatch> getOperateFileSuffixList();

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    boolean deleteOperateFileSuffixById(Integer id);
}
