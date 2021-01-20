package com.tdt.cloud.utils.vector;

import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 计算瓦片行列号
 *
 * @author ack
 */
public class TileStreamIterator {

	public static Stream<Tile> getTiles(Geometry geometry, int minZ, int maxZ) {
		Envelope envelope = geometry.getEnvelopeInternal();
		return getTiles(envelope, minZ, maxZ);
	}

	public static Stream<Tile> getTiles(Envelope envelope, int minZ, int maxZ) {
		Spliterator<Tile> spliterator = IntStream.rangeClosed(minZ, maxZ).mapToObj(z -> z).flatMap(z -> {
			Tile min = getTiles(envelope.getMinX(), envelope.getMaxY(), z);
			Tile max = getTiles(envelope.getMaxX(), envelope.getMinY(), z);
			Stream<Tile> stream = IntStream.rangeClosed(min.getX(), max.getX()).mapToObj(i -> i).flatMap(x -> IntStream
					.rangeClosed(min.getY(), max.getY()).mapToObj(i -> i).map(y -> new Tile(x, y, z)));
			return stream;
		}).spliterator();
		BatchSpliterator<Tile> batchSpliterator = new BatchSpliterator<>(spliterator, 10);
		return StreamSupport.stream(batchSpliterator, true);
	}

	/*public static TileWorker getTilesWorker(Envelope envelope, int minZ, int maxZ) {
		Result tilesResult = getTilesResult(envelope, minZ, maxZ);
		List<Tile> tileList = tilesResult.getTileList();
		return new TileWorker(tileList);
	}

	public static Result getTilesResult(Envelope envelope, int minZ, int maxZ) {
		Result result = new Result();
		//计算最大4326 envelope
		Envelope max4326Envelope = new Envelope();

		List<Tile> tiles = new ArrayList<>();
		for (int z = minZ; z <= maxZ; z++) {
			Tile min = getTiles(envelope.getMinX(), envelope.getMaxY(), z);
			Tile max = getTiles(envelope.getMaxX(), envelope.getMinY(), z);

			max4326Envelope.expandToInclude(min.get4326Envelope());
			max4326Envelope.expandToInclude(max.get4326Envelope());

			int minx = min.getX();
			int maxx = max.getX();
			int miny = min.getY();
			int maxy = max.getY();
			for (int x = minx; x <= maxx; x++) {
				for (int y = miny; y <= maxy; y++) {
					tiles.add(new Tile(x, y, z));
				}
			}
		}

		//赋值
		result.setTileList(tiles);
		result.setMaxEnvelope(max4326Envelope);

		return result;
	}*/


	public static List<Tile> getTiles2(Envelope envelope, int minZ, int maxZ) {
		List<Tile> tiles = new ArrayList<>();
		for (int z = minZ; z <= maxZ; z++) {
			Tile min = getTiles(envelope.getMinX(), envelope.getMaxY(), z);
			Tile max = getTiles(envelope.getMaxX(), envelope.getMinY(), z);
			int minx = min.getX();
			int maxx = max.getX();
			int miny = min.getY();
			int maxy = max.getY();
			for (int x = minx; x <= maxx; x++) {
				for (int y = miny; y <= maxy; y++) {
					tiles.add(new Tile(x, y, z));
				}
			}
		}

		return tiles;
	}

	public static Tile getTiles(double lon, double lat, int z) {
		int x = (int) ((lon + 180.0) / 360.0 * (1 << z));
		int y = (int) ((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2.0
				* (1 << z));
		return new Tile(x, y, z);
	}

	/*public static class Result {
		private List<Tile> tileList;
		private Envelope maxEnvelope;

		public List<Tile> getTileList() {
			return tileList;
		}

		public void setTileList(List<Tile> tileList) {
			this.tileList = tileList;
		}

		public Envelope getMaxEnvelope() {
			return maxEnvelope;
		}

		public void setMaxEnvelope(Envelope maxEnvelope) {
			this.maxEnvelope = maxEnvelope;
		}
	}

	public static class TileItem {
		private Tile leftTopTile;
		private Tile rightBottomTile;

		public TileItem(Tile leftTopTile, Tile rightBottomTile) {
			this.leftTopTile = leftTopTile;
			this.rightBottomTile = rightBottomTile;
		}

		public Tile getLeftTopTile() {
			return leftTopTile;
		}

		public void setLeftTopTile(Tile leftTopTile) {
			this.leftTopTile = leftTopTile;
		}

		public Tile getRightBottomTile() {
			return rightBottomTile;
		}

		public void setRightBottomTile(Tile rightBottomTile) {
			this.rightBottomTile = rightBottomTile;
		}
	}*/
}
