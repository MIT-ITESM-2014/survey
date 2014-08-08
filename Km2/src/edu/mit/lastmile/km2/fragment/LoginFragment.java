package edu.mit.lastmile.km2.fragment;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import edu.mit.lastmile.km2.ApiClientResponse;
import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.dao.KmDataSource;
import edu.mit.lastmile.km2.dao.TokenDataSource;
import edu.mit.lastmile.km2.model.Km;
import edu.mit.lastmile.km2.model.Token;

@EFragment(R.layout.fragment_main)
public class LoginFragment extends Fragment {

	@ViewById(R.id.mailField)
	protected EditText mMailField;
	
	@ViewById(R.id.passwordField)
	protected EditText mPasswordField;
	
	@ViewById(R.id.loadingView)
	protected RelativeLayout mLoadingView; 
	
	@ViewById
	protected TextView loadingText;
	
	@ViewById(R.id.formView)
	protected RelativeLayout mFormView;
	
	@ViewById(R.id.loginBtn)
	protected Button mLoginBtn;
	
	@Bean
	protected App app;
	
	@Bean
	protected TokenDataSource dao;
	
	@Bean
	protected KmDataSource kDao;
	
	@Click(R.id.loginBtn)
	protected void loginBtnOnClick(){
		final Activity activity = getActivity();
		String mail = mMailField.getText().toString();
		String password = mPasswordField.getText().toString();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("mail", mail));
		params.add(new BasicNameValuePair("password", password));
		app.post(Config.Server.Urls.Users.LOGIN, params, new ApiClientResponse() {
			
			@Override
			public void onSuccess(JSONObject response) {
				success(response);
			}
			
			@Override
			public void onLoading() {
				loading();
			}
			
			@Override
			public void onLoaded() {
				loginLoaded();
			}
			
			@Override
			public void onError() {
				error();
			}
			
			@Override
			public Activity getActivity() {
				return activity;
			}
		});
	}
	
	private void success(JSONObject response){
		try{
			if(response.isNull("token")){
				app.invalidLogin();
			}else{
				JSONObject token = response.getJSONObject("token");
				Token t = dao.getToken(token);
				dao.open();
				long tmp = dao.insertToken(t);
				if(tmp != -1){
					login(t);
				}else{
					throw new SQLiteException(getString(R.string.database_error));
				}
			}
		}catch(JSONException e){
			Log.e(Config.LOG_TAG, e.getMessage());
			error();
		}catch (SQLiteException e) {
			app.databaseError();
			Log.e(Config.LOG_TAG, e.getMessage());
			error();
		}finally{
			dao.close();
		}
	}
	
	private void login(Token token){
		setLoadingText(R.string.login_loading_text);
		app.setAuthToken(token);
		fetchData();
	}
	
	private void fetchData(){
		final Activity activity = getActivity();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		app.post(Config.Server.Urls.Kms.ASSIGNED, params, new ApiClientResponse() {
			
			@Override
			public void onSuccess(JSONObject response) {
				fetchDataOnSuccess(response);
			}
			
			@Override
			public void onLoading() {
				// Already loading
			}
			
			@Override
			public void onLoaded() {
				// Nothing
			}
			
			@Override
			public void onError() {
				fetchDataOnError();
			}
			
			@Override
			public Activity getActivity() {
				return activity;
			}
		});
	}
	
	private void fetchDataOnSuccess(JSONObject response){
		try{
			if(response.isNull("contents")){
				app.serverError();
			}else{
				JSONObject contents = response.getJSONObject("contents");
				Km k = kDao.getKm(contents);
				kDao.open();
				long tmp = kDao.insert(k);
				if(tmp != -1){
					app.setUserKm(k);
					loadKmData();
					app.startRootActivity(getActivity());
				}else{
					throw new SQLiteException(getString(R.string.database_error));
				}
			}
		}catch(JSONException e){
			Log.e(Config.LOG_TAG, e.getMessage());
			error();
		}catch (SQLiteException e) {
			app.databaseError();
			Log.e(Config.LOG_TAG, e.getMessage());
			error();
		}finally{
			kDao.close();
		}
	}
	
	private void loadKmData(){
		final Activity activity = getActivity();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		app.post(Config.Server.Urls.Kms.DATA, params, new ApiClientResponse() {
			
			@Override
			public void onSuccess(JSONObject response) {
				loadKmDataOnSuccess(response);
			}
			
			@Override
			public void onLoading() {
				// Still loading
			}
			
			@Override
			public void onLoaded() {
				// Not used
			}
			
			@Override
			public void onError() {
				loadKmDataOnError();
			}
			
			@Override
			public Activity getActivity() {
				return activity;
			}
		});
	}
	
	private void loadKmDataOnSuccess(JSONObject response){
		Log.d(Config.LOG_TAG, response.toString());
	}
	
	private void loadKmDataOnError(){
		app.serverError();
		// TODO retry view
	}
	
	private void fetchDataOnError(){
		app.serverError();
		error();
	}
	
	private void error(){
		app.authError(getActivity());
		loaded();
	}
	
	private void loading(){
		mFormView.setVisibility(RelativeLayout.GONE);
		mLoadingView.setVisibility(RelativeLayout.VISIBLE);
	}
	
	private void setLoadingText(int resource){
		loadingText.setText(resource);
	}
	
	private void loginLoaded(){
		// Remain loading for data download
	}
	
	private void loaded(){
		mFormView.setVisibility(RelativeLayout.VISIBLE);
		mLoadingView.setVisibility(RelativeLayout.GONE);
	}
	
}