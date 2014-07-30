package edu.mit.lastmile.km2.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.androidannotations.annotations.EBean;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.model.TrafficCount;

@EBean
public class TrafficCountDataSource extends DataSource {
	/*
	 * Table structure mimics km2 original database fields, but static column naming is based on km2_v2 category names. 
	 */
	public static final String CREATE_TABLE = "CREATE TABLE traffic_counts(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," 
											+ " started_at DATETIME DEFAULT CURRENT_TIMESTAMP, ended_at DATETIME DEFAULT CURRENT_TIMESTAMP,"
											+ " cars INTEGER DEFAULT 0, bikes INTEGER DEFAULT 0, motorbikes INTEGER DEFAULT 0, pickup_trucks INTEGER DEFAULT 0, vans INTEGER DEFAULT 0,"
											+ " rigid_trucks INTEGER DEFAULT 0, articulated_trucks INTEGER DEFAULT 0, pedestrians INTEGER DEFAULT 0, status INTEGER DEFAULT 0)";
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS traffic_counts";
	public static final String TABLE = "traffic_counts";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_STARTED_AT = "started_at";
	public static final String COLUMN_ENDED_AT = "started_at";
	public static final String COLUMN_CARS = "cars";
	public static final String COLUMN_NON_MOTORIZED = "bikes";
	public static final String COLUMN_MOTORIZED = "motorbikes";
	public static final String COLUMN_PICKUP_VAN = "pickup_trucks";
	public static final String COLUMN_SMALL_TRUCKS = "vans";
	public static final String COLUMN_MEDIUM_TRUCKS = "rigid_trucks";
	public static final String COLUMN_LARGE_TRUCKS = "articulated_trucks";
	public static final String COLUMN_OTHERS = "pedestrians";
	// Control flags
	public static final String COLUMN_STATUS = "status";
	
	public TrafficCountDataSource(){}
	
	public long insert(TrafficCount element){
		return getDb().insert(TABLE, null, getContentValues(element));
	}
	
	public ArrayList<TrafficCount> getElements() throws ParseException{
		ArrayList<TrafficCount> list = new ArrayList<TrafficCount>();
		Cursor result = selectAll();
		Log.d(Config.LOG_TAG, "getElements");
		while(result.moveToNext()){
			list.add(fromCursor(result));
		}
		return list;
	}
	
	private Cursor selectAll(){
		return getDb().query(TABLE, null, null, null, null, null, null);
	}
	
	public TrafficCount fromCursor(Cursor c) throws ParseException{
		TrafficCount el = new TrafficCount();
		el.setId(c.getInt(0));
		String started_at = c.getString(1);
		Date s_at = stringToDate(started_at);
		String ended_at = c.getString(2);
		Date e_at = stringToDate(ended_at);
		el.setStartedAt(s_at);
		el.setEndedAt(e_at);
		el.setCars(c.getInt(3));
		el.setBikes(c.getInt(4));
		el.setMotorbikes(c.getInt(5));
		el.setPickupTrucks(c.getInt(6));
		el.setVans(c.getInt(7));
		el.setRigidTrucks(c.getInt(8));
		el.setArticulatedTrucks(c.getInt(9));
		el.setPedestrians(c.getInt(10));
		el.setStatus(c.getInt(11));
		Log.d(Config.LOG_TAG, el.toString());
		return el;
	}
	
	public static ContentValues getContentValues(TrafficCount element){
		ContentValues cv = new ContentValues();
		// TODO timestamps started_at, ended_at
		if(element.getStartedAt() != null){
			String started_at = dateToString(element.getStartedAt());
			cv.put(COLUMN_STARTED_AT, started_at);
		}
		if(element.getEndedAt() != null){
			String ended_at = dateToString(element.getStartedAt());
			cv.put(COLUMN_ENDED_AT, ended_at);			
		}
		cv.put(COLUMN_CARS, element.getCars());
		cv.put(COLUMN_NON_MOTORIZED, element.getBikes());
		cv.put(COLUMN_MOTORIZED, element.getMotorbikes());
		cv.put(COLUMN_PICKUP_VAN, element.getPickupTrucks());
		cv.put(COLUMN_SMALL_TRUCKS, element.getVans());
		cv.put(COLUMN_MEDIUM_TRUCKS, element.getRigidTrucks());
		cv.put(COLUMN_LARGE_TRUCKS, element.getArticulatedTrucks());
		cv.put(COLUMN_OTHERS, element.getPedestrians());
		return cv;
	}
	
}
