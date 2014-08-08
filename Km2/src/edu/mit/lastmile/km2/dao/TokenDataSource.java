package edu.mit.lastmile.km2.dao;

import org.androidannotations.annotations.EBean;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import edu.mit.lastmile.km2.model.Token;

@EBean
public class TokenDataSource extends DataSource {

	public static final String CREATE_TABLE = "CREATE TABLE tokens(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, app_user_id INTEGER, token, secret, identity)";
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS tokens";
	private static final String COLUMN_TOKEN = "token";
	private static final String COLUMN_SECRET = "secret";
	private static final String TABLE_NAME = "tokens";
	private String[] columns = {
			COLUMN_ID,
			COLUMN_TOKEN,
			COLUMN_SECRET
	};
	
	public TokenDataSource() {}
	
	public Token getToken(JSONObject data) throws JSONException {
		String token = data.getString("token");
		String secret = data.getString("secret");
		return new Token(token, secret);
	}
	
	public long insertToken(Token t) {
		return insertToken(t.getToken(), t.getSecret());
	}
	
	public long insertToken(String token, String secret) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_TOKEN, token);
		values.put(COLUMN_SECRET, secret);
		return getDb().insert(TABLE_NAME, null, values);
	}

	public int deleteTokens() {
		return getDb().delete(TABLE_NAME, null, null);
	}

	public Token getToken() {
		Cursor c = getDb().query(TABLE_NAME, columns, null, null, null, null, null, "0,1");
		Token t = null;
		if(c.moveToFirst()){
			t = cursorToToken(c);
		}
		c.close();
		return t;
	}
	
	public Token cursorToToken(Cursor c) {
		Token token = new Token();
		token.setId(c.getLong(0));
		token.setToken(c.getString(1));
		token.setSecret(c.getString(2));
		return token;
	}
	
}
