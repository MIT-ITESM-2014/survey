package edu.mit.lastmile.km2.fragment;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import edu.mit.lastmile.km2.ApiClientResponse;
import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.R;

@EFragment(R.layout.fragment_main)
public class LoginFragment extends Fragment {

	@ViewById(R.id.mailField)
	EditText mMailField;
	
	@ViewById(R.id.passwordField)
	EditText mPasswordField;
	
	@ViewById(R.id.loadingView)
	RelativeLayout mLoadingView; 
	
	@ViewById(R.id.formView)
	RelativeLayout mFormView;
	
	@ViewById(R.id.loginBtn)
	Button mLoginBtn;
	
	@Bean
	App app;
	
	@Click(R.id.loginBtn)
	void loginBtnOnClick(){
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
				loaded();
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
		Log.v(Config.LOG_TAG, response.toString());
		app.startRootActivity(getActivity());
	}
	
	private void error(){
		loaded();
		app.authError(getActivity());
	}
	
	private void loading(){
		mFormView.setVisibility(RelativeLayout.GONE);
		mLoadingView.setVisibility(RelativeLayout.VISIBLE);
	}
	
	private void loaded(){
		mFormView.setVisibility(RelativeLayout.VISIBLE);
		mLoadingView.setVisibility(RelativeLayout.GONE);
	}
	
}