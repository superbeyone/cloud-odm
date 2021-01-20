package com.tdt.cloud.utils.coordinate;

import java.io.Serializable;

/**
 * 要转换坐标的封装
 * 
 * @author ack
 *
 */
public class Coordinate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6375295591479741946L;
	
	private int level;                               // 等级
	private String projection;                       // 投影方式
	private String dbName;                           // 数据库名称
	private String time = "00000000";                // 时间
	private String format;                           // 图片格式
	private String tableName = "tiles";              // collection name
	private double leftBottomLatitude;               // 坐下角纬度 column
	private double leftBottomLongitude;              // 左下角经度 row
	private double rightUpLatitude;                  // 右上角纬度 column
	private double rightUpLongitude;                 // 右下角经度 row
	
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getProjection() {
		return projection;
	}
	public void setProjection(String projection) {
		this.projection = projection;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public double getLeftBottomLatitude() {
		return leftBottomLatitude;
	}
	public void setLeftBottomLatitude(double leftBottomLatitude) {
		this.leftBottomLatitude = leftBottomLatitude;
	}
	public double getLeftBottomLongitude() {
		return leftBottomLongitude;
	}
	public void setLeftBottomLongitude(double leftBottomLongitude) {
		this.leftBottomLongitude = leftBottomLongitude;
	}
	public double getRightUpLatitude() {
		return rightUpLatitude;
	}
	public void setRightUpLatitude(double rightUpLatitude) {
		this.rightUpLatitude = rightUpLatitude;
	}
	public double getRightUpLongitude() {
		return rightUpLongitude;
	}
	public void setRightUpLongitude(double rightUpLongitude) {
		this.rightUpLongitude = rightUpLongitude;
	}

	@Override
	public String toString() {
		String s = "{"
				+ "level : " + level
				+ ", projection : " + projection
				+ ", leftBottomLatitude : " + leftBottomLatitude
				+ ", leftBottomLongitude : " + leftBottomLongitude
				+ ", rightUpLatitude : " + rightUpLatitude
				+ ", rightUpLongitude : " + rightUpLongitude
				+ "}" ;
		 
		return s;
	}
	
}
