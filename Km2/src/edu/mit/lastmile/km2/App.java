package edu.mit.lastmile.km2;

import java.util.List;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.RootContext;
import org.apache.http.NameValuePair;

import edu.mit.lastmile.km2.activity.RootActivity_;
import edu.mit.lastmile.km2.model.Token;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

@EBean(scope = Scope.Singleton)
public class App {

	@RootContext
	Context context;
	
	@Bean
	ApiClient client;
	
	private Token authToken;

	public void authError(Activity activity){
		String msg = activity.getString(R.string.auth_error);
		//logout(activity);
		showToast(activity, msg);
	}
	
	public void post(String path, List<NameValuePair> params, ApiClientResponse responseHandler){
		getClient().post(path, params, responseHandler);
	}

	public void databaseError(Context c){
		showToast(c, c.getString(R.string.database_error));
	}
	
	public void databaseSuccess(Context c){
		showToast(c, c.getString(R.string.database_success));
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
	
	public void startRootActivity(Activity context){
		Intent intent = new Intent(context, RootActivity_.class);
		context.startActivity(intent);
	}
	
	public boolean hasSession() {
		return getAuthToken() != null;
	}
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Token getAuthToken() {
		return this.authToken;
	}

	public void setAuthToken(Token authToken) {
		this.authToken = authToken;
	}
	
	public ApiClient getClient() {
		return client;
	}

	public void setClient(ApiClient client) {
		this.client = client;
	}
}