package edu.mit.lastmile.km2.dao;

import java.util.ArrayList;

import org.androidannotations.annotations.EBean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteStatement;
import android.net.ParseException;
import android.util.Log;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.model.Block;

@EBean
public class BlockDataSource extends DataSource {

	public static final String CREATE_TABLE = "CREATE TABLE blocks(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, km_id INTEGER, research_id INTEGER)";
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS blocks";
	public static final String TABLE = "blocks";
	public static final String COLUMN_KM_ID = "km_id";
	public static final String COLUMN_RESEARCH_ID = "research_id";
	
	public BlockDataSource(){}
	
	public ArrayList<Block> getElements(){
		ArrayList<Block> list = new ArrayList<Block>();
		Cursor result = selectAll();
		while(result.moveToNext()){
			list.add(fromCursor(result));
		}
		return list;
	}
	
	public ArrayList<Block> getElements(JSONArray objects) throws JSONException{
		ArrayList<Block> list = new ArrayList<Block>();
		int length = objects.length();
		for(int i = 0; i < length; ++i){
			Block b = fromJson(objects.getJSONObject(i));
			list.add(b);
		}
		return list;
	}
	
	private Cursor selectAll(){
		return getDb().query(TABLE, null, null, null, null, null, null);
	}
	
	public int massInsert(ArrayList<Block> objects) throws SQLException{
		int result = objects.size();
		getDb().beginTransaction();
		try{
			SQLiteStatement stmt = getDb().compileStatement("INSERT INTO " + TABLE + " (" 
					+ COLUMN_KM_ID + ", " 
					+ COLUMN_RESEARCH_ID + ") VALUES(?, ?)");
			for(Block obj : objects){
				stmt.bindLong(1, obj.getKmId());
				stmt.bindLong(2, obj.getResearchId());
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
	
	public static Block fromJson(JSONObject object) throws JSONException{
		Block element = new Block();
		element.setId(object.getLong("id"));
		element.setKmId(object.getLong("km_id"));
		element.setResearchId(object.getLong("research_id"));
		return element;
	}
	
	public static Block fromCursor(Cursor c) throws ParseException{
		Block s = new Block();
		s.setId(c.getLong(0));
		s.setKmId(c.getLong(1));
		s.setResearchId(c.getLong(2));
		return s;
	}
	
}
