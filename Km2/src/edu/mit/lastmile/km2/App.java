package edu.mit.lastmile.km2;

import java.util.List;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.EBean.Scope;
import org.apache.http.NameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.widget.Toast;
import edu.mit.lastmile.km2.activity.MainActivity_;
import edu.mit.lastmile.km2.activity.RootActivity_;
import edu.mit.lastmile.km2.dao.KmDataSource;
import edu.mit.lastmile.km2.dao.TokenDataSource;
import edu.mit.lastmile.km2.model.Km;
import edu.mit.lastmile.km2.model.Token;

@EBean(scope = Scope.Singleton)
public class App {
	
	@RootContext
	protected Context context;
	
	@Bean
	protected ApiClient client;
	
	@Bean
	protected TokenDataSource dao;

	@Bean
	protected KmDataSource kDao;
	
	private Token authToken;
	private Km userKm;

	public void authError(Activity activity){
		logout(activity);
		String msg = activity.getString(R.string.auth_error);
		showToast(activity, msg);
	}
	
	public void logout(Activity activity){
		try{
			dao.open();
			dao.deleteTokens();
			dao.close();
			// TODO Manage kms
			//kDao.open();
			//kDao.deleteKms();
			//kDao.close();
			setAuthToken(null);
			setUserKm(null);
			startMainActivity(activity);
		}catch(SQLException e){
			databaseError();
		}finally{
			dao.close();
			//kDao.close();
		}
	}
	
	public void post(String path, List<NameValuePair> params, ApiClientResponse responseHandler){
		getClient().post(path, params, responseHandler);
	}

	public void toast(String s){
		showToast(context, s);
	}

	public void invalidLogin(){
		showToast(context, context.getString(R.string.login_invalid));
	}
	
	public void locationError(){
		showToast(context, context.getString(R.string.location_error));
	}
	
	public void databaseError(){
		showToast(context, context.getString(R.string.database_error));
	}

	public void serverError(){
		showToast(context, context.getString(R.string.server_error));
	}
	
	public void camaraError(){
		showToast(context, context.getString(R.string.camara_error));
	}
	
	public void camaraUnavailable(){
		showToast(context, context.getString(R.string.camara_unavailable));
	}
	
	public void databaseSuccess(){
		showToast(context, context.getString(R.string.database_success));
	}
	
	public static void showLongToast(Context c, CharSequence text) {
		Context ctx = c.getApplicationContext();
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(ctx, text, duration);
		toast.show();		
	}
	
	public static void showToast(Context c, CharSequence text) {
		Context ctx = c.getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(ctx, text, duration);
		toast.show();
	}

	public void startMainActivity(Activity context){
		Intent intent = new Intent(context, MainActivity_.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear back stack
		context.startActivity(intent);
	}
	
	public void startRootActivity(Activity context){
		Intent intent = new Intent(context, RootActivity_.class);
		context.startActivity(intent);
	}
	
	public boolean hasSession() {
		return getAuthToken() != null;
	}

	public Token getAuthToken() {
		return this.authToken;
	}

	public void setAuthToken(Token authToken) {
		this.authToken = authToken;
	}
	
	public Km getUserKm(){
		return this.userKm;
	}
	
	public void setUserKm(Km km){
		this.userKm = km;
	}
	
	public ApiClient getClient() {
		return client;
	}

	public void setClient(ApiClient client) {
		this.client = client;
	}
}