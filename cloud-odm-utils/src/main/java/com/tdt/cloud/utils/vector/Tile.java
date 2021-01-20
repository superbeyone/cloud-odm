package com.tdt.cloud.utils.vector;

import org.locationtech.jts.geom.Envelope;

/**
 * 瓦片行列号等级信息
 * 
 * @author ack
 *
 */
public class Tile {
	protected int x;
	protected int y;
	protected int z; // 等级
	
	public Tile() {
		
	}

	public Tile(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Tile that = (Tile) o;
		return x == that.x && y == that.y && z == that.z;
	}



	public static double tile2lon(int x, int z) {
		return x / Math.pow(2.0, z) * 360.0 - 180;
	}

	public static double tile2lat(int y, int z) {
		double n = Math.PI - (2.0 * Math.PI * y) / Math.pow(2.0, z);
		return Math.toDegrees(Math.atan(Math.sinh(n)));
	}

	public Envelope envelope() {
		double north = tile2lat(y, z);
		double south = tile2lat(y + 1, z);
		double west = tile2lon(x, z);
		double east = tile2lon(x + 1, z);
		return new Envelope(west, east, south, north);
	}
	public Envelope get4326Envelope() {
		double north = tile2lat(y, z);
		double south = tile2lat(y + 1, z);
		double west = tile2lon(x, z);
		double east = tile2lon(x + 1, z);
		return new Envelope(west, east, south, north);
	}

	@Override
	public String toString() {
		return "Tile [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	

}
