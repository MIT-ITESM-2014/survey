package edu.mit.lastmile.km2.dao;

import java.text.ParseException;
import java.util.ArrayList;

import org.androidannotations.annotations.EBean;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.model.Shop;

@EBean
public class ShopDataSource extends DataSource {
	
	public static final String CREATE_TABLE = "CREATE TABLE shops(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, km_id INTEGER,"
											+ " street_id INTEGER, block_id INTEGER, shop_type TEXT, shop_size TEXT, name TEXT, front_length INTEGER,"
											+ " total_floors INTEGER, has_loading_area INTEGER, loading_area_type INTEGER,"
											+ " lat REAL, lng REAL, notes TEXT, image TEXT, shop_id TEXT)";
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS shops";
	public static final String TABLE = "shops";
	public static final String COLUMN_KM_ID = "km_id";
	public static final String COLUMN_STREET_ID = "street_id";
	public static final String COLUMN_BLOCK_ID = "block_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_SHOP_TYPE = "shop_type";
	public static final String COLUMN_SHOP_SIZE = "shop_size";
	public static final String COLUMN_FRONT_LENGTH = "front_length";
	public static final String COLUMN_TOTAL_FLOORS = "total_floors";
	public static final String COLUMN_HAS_LOADING_AREA = "has_loading_area";
	public static final String COLUMN_LOADING_AREA_TYPE = "loading_area_type";
	public static final String COLUMN_LAT = "lat";
	public static final String COLUMN_LNG = "lng";
	public static final String COLUMN_NOTES = "notes";
	public static final String COLUMN_IMAGE = "image";
	public static final String COLUMN_SHOP_ID = "shop_id";
	
	public ShopDataSource(){}
	
	public long insert(Shop element){
		return getDb().insert(TABLE, null, getContentValues(element));
	}
	
	public ArrayList<Shop> getElements() throws ParseException{
		ArrayList<Shop> list = new ArrayList<Shop>();
		Cursor result = selectAll();
		Log.d(Config.LOG_TAG, "Start -Shops-");
		while(result.moveToNext()){
			list.add(fromCursor(result));
		}
		Log.d(Config.LOG_TAG, "End -Shops-");
		return list;
	}
	
	public ArrayList<Shop> findBySegment(int streetId, int blockId) throws ParseException{
		ArrayList<Shop> list = new ArrayList<Shop>();
		Cursor result = filterBySegment(streetId, blockId);
		while(result.moveToNext()){
			list.add(fromCursor(result));
		}
		return list;
	}
	
	private Cursor filterBySegment(int streetId, int blockId){
		String[] args = {
			"" + streetId,
			"" + blockId
		};
		return getDb().query(TABLE, null, "WHERE shops.street_id = ? AND shops.block_id = ?", args, null, null, null);
	}
	
	private Cursor selectAll(){
		return getDb().query(TABLE, null, null, null, null, null, null);
	}
	
	public Shop fromCursor(Cursor c) throws ParseException {
		Shop s = new Shop();
		s.setId(c.getLong(0));
		s.setKmId(c.getInt(1));
		s.setStreetId(c.getLong(2));
		s.setBlockId(c.getLong(3));
		s.setShopType(c.getString(4));
		s.setShopSize(c.getString(5));
		s.setName(c.getString(6));
		s.setFrontLength(c.getDouble(7));
		s.setHasLoadingArea(c.getInt(8));
		s.setLoadingAreaType(c.getInt(9));
		s.setLat(c.getDouble(10));
		s.setLng(c.getDouble(11));
		s.setNotes(c.getString(12));
		s.setImage(c.getString(13));
		s.setShopId(c.getString(14));
		Log.d(Config.LOG_TAG, s.toString());
		return s;
	}
	
	public static ContentValues getContentValues(Shop element){
		ContentValues cv = new ContentValues();
		Log.d(Config.LOG_TAG, element.toString());
		cv.put(COLUMN_KM_ID, element.getKmId());
		cv.put(COLUMN_STREET_ID, element.getKmId());
		cv.put(COLUMN_BLOCK_ID, element.getBlockId());
		cv.put(COLUMN_NAME, element.getName());
		cv.put(COLUMN_SHOP_TYPE, element.getShopType());
		cv.put(COLUMN_SHOP_SIZE, element.getShopSize());
		cv.put(COLUMN_FRONT_LENGTH, element.getFrontLength());
		cv.put(COLUMN_TOTAL_FLOORS, element.getTotalFloors());
		cv.put(COLUMN_HAS_LOADING_AREA, element.getHasLoadingArea());
		cv.put(COLUMN_LOADING_AREA_TYPE, element.getLoadingAreaType());
		cv.put(COLUMN_LAT, element.getLat());
		cv.put(COLUMN_LNG, element.getLng());
		cv.put(COLUMN_NOTES, element.getNotes());
		if(element.hasImage()){
			cv.put(COLUMN_IMAGE, element.getImage().toString());
		}
		cv.put(COLUMN_SHOP_ID, element.getShopId());
		return cv;
	}
	
}
