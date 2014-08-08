package edu.mit.lastmile.km2.dao;

import org.androidannotations.annotations.EBean;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.model.Km;

@EBean
public class KmDataSource extends DataSource {
	/*
	 * This data source and the related model do not represent the complete information set
	 * stored in the system db. It is only used for relation keeping and UI display.
	 * */
	public static final String CREATE_TABLE = "CREATE TABLE kms(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, km_id INTEGER,"
											+ " name, location, comments)";
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS kms";
	public static final String TABLE = "kms";
	public static final String COLUMN_KM_ID = "km_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_COMMENTS = "comments";
	public static final String[] columns = {
		COLUMN_ID,
		COLUMN_KM_ID,
		COLUMN_NAME,
		COLUMN_LOCATION,
		COLUMN_COMMENTS
	};
	
	public long insert(Km element){
		return getDb().insert(TABLE, null, getContentValues(element));
	}
	
	public Cursor selectKm(){
		return getDb().query(TABLE, columns, null, null, null, null, null);
	}
	
	public int deleteKms() {
		return getDb().delete(TABLE, null, null);
	}
	
	public Km getKm(JSONObject data) throws JSONException{
		Km km  = new Km();
		km.setKmId(data.getLong("id"));
		km.setName(data.getString("name"));
		km.setLocation(data.getString("location"));
		km.setComments(data.getString("comments"));
		return km;
	}
	
	public Km getKm(){
		Km k = null;
		Cursor c = selectKm();
		if(c.moveToFirst()){
			k = fromCursor(c);
		}
		return k;
	}
	
	public Km fromCursor(Cursor c){
		Km km = new Km();
		km.setId(c.getLong(0));
		km.setKmId(c.getLong(1));
		km.setName(c.getString(2));
		km.setLocation(c.getString(3));
		km.setComments(c.getString(4));
		Log.d(Config.LOG_TAG, km.toString());
		return km;
	}
	
	public ContentValues getContentValues(Km element){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_KM_ID, element.getKmId());
		cv.put(COLUMN_NAME, element.getName());
		cv.put(COLUMN_LOCATION, element.getLocation());
		String comments = element.getComments();
		if(comments != null){
			cv.put(COLUMN_COMMENTS, comments);
		}
		return cv;
	}
	
}
