package com.tdt.cloud.odm.pojo.grid;

import com.vividsolutions.jts.geom.Geometry;
import org.opengis.geometry.BoundingBox;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className GeometryAndBoundingBox
 * @description
 * @date 2020-12-03 15:05
 **/

public class GeometryAndBoundingBox {

    private BoundingBox boundingBox;

    private Geometry geometry;

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
