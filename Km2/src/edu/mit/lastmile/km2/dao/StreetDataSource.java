package edu.mit.lastmile.km2.dao;

import java.util.ArrayList;

import org.androidannotations.annotations.EBean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.net.ParseException;
import android.util.Log;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.model.Street;

@EBean
public class StreetDataSource extends DataSource {

	public static final String CREATE_TABLE = "CREATE TABLE streets(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, km_id INTEGER, block_id INTEGER, research_id INTEGER)";
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS streets";	
	public static final String TABLE = "streets";
	public static final String COLUMN_KM_ID = "km_id";
	public static final String COLUMN_BLOCK_ID = "block_id";
	public static final String COLUMN_RESEARCH_ID = "research_id";
	
	public StreetDataSource(){}
	
	public ArrayList<Street> getElements() throws ParseException{
		ArrayList<Street> list = new ArrayList<Street>();
		Cursor result = selectAll();
		while(result.moveToNext()){
			list.add(fromCursor(result));
		}
		return list;
	}
	
	public ArrayList<Street> getElements(JSONArray objects) throws JSONException{
		ArrayList<Street> list = new ArrayList<Street>();
		int length = objects.length();
		for(int i = 0; i < length; ++i){
			Street s = fromJSON(objects.getJSONObject(i));
			list.add(s);
		}
		return list;
	}
	
	public ArrayList<Street> findByBlock(long blockId){
		ArrayList<Street> list = new ArrayList<Street>();
		Cursor result = filterByBlock(blockId);
		while(result.moveToNext()){
			list.add(fromCursor(result));
		}
		return list;
	}
	
	private Cursor filterByBlock(long blockId){
		String[] args = {
			"" + blockId
		};
		return getDb().query(TABLE, null, "streets.block_id = ?", args, null, null, null);
	}
	
	private Cursor selectAll(){
		return getDb().query(TABLE, null, null, null, null, null, null);
	}
	
	public int massInsert(ArrayList<Street> objects){
		int result = objects.size();
		getDb().beginTransaction();
		try{
			SQLiteStatement stmt = getDb().compileStatement("INSERT INTO " + TABLE + " (" 
					+ COLUMN_KM_ID + ", "
					+ COLUMN_BLOCK_ID + ", "
					+ COLUMN_RESEARCH_ID + ") VALUES(?, ?, ?)");
			for(Street obj : objects){
				stmt.bindLong(1, obj.getKmId());
				stmt.bindLong(2, obj.getBlockId());
				stmt.bindLong(3, obj.getResearchId());
				stmt.executeInsert();
			}
			getDb().setTransactionSuccessful();
		}catch(IllegalStateException e){
			result = -1;
			Log.e(Config.LOG_TAG, "" + e.getLocalizedMessage());
		}finally{
			getDb().endTransaction();
		}
		return result;
	}
	
	public static Street fromJSON(JSONObject object) throws JSONException{
		Street element = new Street();
		element.setId(object.getLong("id"));
		element.setKmId(object.getLong("km_id"));
		element.setBlockId(object.getLong("block_id"));
		element.setResearchId(object.getLong("research_id"));
		return element;
	}
	
	public static Street fromCursor(Cursor c) throws ParseException{
		Street s = new Street();
		s.setId(c.getLong(0));
		s.setKmId(c.getLong(1));
		s.setBlockId(c.getLong(2));
		s.setResearchId(c.getLong(3));
		return s;
	}
	
}
