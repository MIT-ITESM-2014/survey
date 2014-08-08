package edu.mit.lastmile.km2.util.location;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.util.ErrorDialog;

@EBean
public class LocationHelper implements GooglePlayServicesClient.ConnectionCallbacks, 
									GooglePlayServicesClient.OnConnectionFailedListener,
									LocationListener {
	
	public final static int ERROR_RESULT_CODE = 9000;
	private LocationHelperListener mHandler;
	private LocationClient mClient;
	private LocationRequest mRequest;
	private Activity mActivity;
	private boolean mUpdatesRequested = false;
	
	@Bean
	protected App mApp;
	
	@RootContext
	protected Context mContext;
	
	public LocationHelper(){}
	
	public void setUp(Activity activity){
		mActivity = activity;
		mClient = new LocationClient(mContext, this, this);
	}
	
	public void start(){
		mClient.connect();
	}
	
	public boolean updatesRequested(){
		return mUpdatesRequested;
	}
	
	public Location getLocation(){
		if(serviceConnected()){
			return mClient.getLastLocation();
		}else{
			return null;
		}
	}
	
	public void stop(){
		if(mUpdatesRequested && mClient.isConnected()){
			mClient.removeLocationUpdates(this);
		}
		mClient.disconnect();
	}
	
	public void requestUpdates(LocationHelperListener handler){
		mUpdatesRequested = true;
		mHandler = handler;
		mRequest = generateRequest();
		mClient.requestLocationUpdates(mRequest, this);
	}
	
	private LocationRequest generateRequest(){
		LocationRequest request = LocationRequest.create();
		request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		request.setInterval(Config.Location.UPDATE_INTERVAL);
		request.setFastestInterval(Config.Location.FAST_INTERVAL);
		return request;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		mHandler.update(location);
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if(result.hasResolution()){
			try{
				result.startResolutionForResult(mActivity, ERROR_RESULT_CODE);
			}catch(IntentSender.SendIntentException e){
				Log.e(Config.LOG_TAG, "onConnectionFailed: " + e.getLocalizedMessage());
			}
		}else{
			showErrorDialog(result.getErrorCode());
		}
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		Log.d(Config.LOG_TAG, "onConnected");
	}

	@Override
	public void onDisconnected() {
		Log.d(Config.LOG_TAG, "onDisconnected");
	}
	
	private boolean serviceConnected(){
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
		if(resultCode == ConnectionResult.SUCCESS){
			Log.d(Config.LOG_TAG, "Google Play Service ready...");
			return true;
		}else{
			showErrorDialog(resultCode);
			return false;
		}
	}
	
	private void showErrorDialog(int errorCode){
		Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(errorCode, mActivity, ERROR_RESULT_CODE);
		if(errorDialog != null){
			ErrorDialog errorFragment = new ErrorDialog();
			errorFragment.setDialog(errorDialog);
			errorFragment.show(mActivity.getFragmentManager(), Config.LOG_TAG);
		}		
	}
	
}