package edu.mit.lastmile.km2.dao;

import java.text.ParseException;
import java.util.ArrayList;

import org.androidannotations.annotations.EBean;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.model.DeliveryShop;

@EBean
public class DeliveryShopDataSource extends DataSource {

	public static final String CREATE_TABLE = "CREATE TABLE delivery_shops(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, km_id INTEGER,"
			+ " shop_id TEXT)";
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS delivery_shops";
	public static final String TABLE = "delivery_shops";
	public static final String COLUMN_KM_ID = "km_id";
	public static final String COLUMN_SHOP_ID = "shop_id";
	
	public DeliveryShopDataSource(){}
	
	public long insert(DeliveryShop element){
		return getDb().insert(TABLE, null, getContentValues(element));
	}
	
	public ArrayList<DeliveryShop> getElements() throws ParseException{
		ArrayList<DeliveryShop> list = new ArrayList<DeliveryShop>();
		Cursor result = selectAll();
		while(result.moveToNext()){
			list.add(fromCursor(result));
		}
		return list;
	}
	
	private Cursor selectAll(){
		return getDb().query(TABLE, null, null, null, null, null, null);
	}
	
	public DeliveryShop fromCursor(Cursor c) throws ParseException {
		DeliveryShop ds = new DeliveryShop();
		ds.setId(c.getLong(0));
		ds.setKmId(c.getLong(1));
		ds.setShopId(c.getString(2));
		Log.d(Config.LOG_TAG, ds.toString());
		return ds;
	}
	
	public static ContentValues getContentValues(DeliveryShop element){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_KM_ID, element.getKmId());
		cv.put(COLUMN_SHOP_ID, element.getShopId());
		return cv;
	}
	
}
