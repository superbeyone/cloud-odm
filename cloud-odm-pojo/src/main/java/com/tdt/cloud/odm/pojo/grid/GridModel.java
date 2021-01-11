package com.tdt.cloud.odm.pojo.grid;

import com.tdt.odm.pojo.data.RangeSpaceModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className GridModel
 * @description
 * @date 2020-12-03 14:43
 **/

public class GridModel implements Serializable {


    private static final long serialVersionUID = 2455265501753222587L;

    private List<RangeSpaceModel> rangeSpaceModels;

    private List<RowAndColModel> rowAndColModels;

    public List<RangeSpaceModel> getRangeSpaceModels() {
        return rangeSpaceModels;
    }

    public void setRangeSpaceModels(List<RangeSpaceModel> rangeSpaceModels) {
        this.rangeSpaceModels = rangeSpaceModels;
    }

    public List<RowAndColModel> getRowAndColModels() {
        return rowAndColModels;
    }

    public void setRowAndColModels(List<RowAndColModel> rowAndColModels) {
        this.rowAndColModels = rowAndColModels;
    }
}
