package edu.mit.lastmile.km2.fragment.shops;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.Fragment;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.ShopsActivity;
import edu.mit.lastmile.km2.util.location.LocationHelper;
import edu.mit.lastmile.km2.util.location.LocationHelperListener;
import edu.mit.lastmile.km2.util.net.ConnectionHelper;

@EFragment(R.layout.fragment_shops_map)
public class ShopMapFragment extends Fragment {
	
	private GoogleMap mMap;
	private Marker mMarker;
	
	@FragmentById
	protected MapFragment mapFragment;
	
	@ViewById
	protected TextView noConnectionLabel;
	
	@ViewById
	protected Button locationBtn;
	
	@ViewById 
	protected ProgressBar locationProgress;
	
	@Bean
	protected LocationHelper mLocationHelper;

	@Bean
	protected ConnectionHelper mConnHelper;
	
	@Bean
	protected App mApp;
	
	@AfterViews
	protected void initViews(){
		initConnection();
		initMap();
		initLocation();
	}
	
	private void initConnection(){
		if(!mConnHelper.isConnected()){
			mapFragment.getView().setVisibility(View.GONE);
			noConnectionLabel.setVisibility(TextView.VISIBLE);
		}
	}
	
	private void initMap(){
		mMap = mapFragment.getMap();
	}
	
	private void initLocation(){
		mLocationHelper.setUp(getActivity());
		mLocationHelper.start();
	}
	
	private void loading(){
		locationBtn.setText(R.string.shops_register_location_loading);
		locationProgress.setVisibility(ProgressBar.VISIBLE);
	}
	
	private void loaded(){
		locationProgress.setVisibility(ProgressBar.GONE);
		locationBtn.setText(R.string.shops_register_location_btn);
	}
	
	private void error(){
		loaded();
		mApp.locationError();
		locationBtn.setText(R.string.shops_register_location_error);
	}
	
	private void locationUpdate(Location location){
		Double lat = location.getLatitude();
		Double lng = location.getLongitude();
		LatLng position = new LatLng(lat, lng);
		Log.d(Config.LOG_TAG, "Lat: " + lat + ", Lng: " + lng);
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, Config.Map.ZOOM));
		if(mMarker == null){
			mMarker = mMap.addMarker(new MarkerOptions().position(position).draggable(Config.Map.Marker.DRAGGABLE));	
		}
		mLocationHelper.stop();
		loaded();
	}
	
	@Click
	protected void locationBtn(){
		if(mMarker == null){
			if(!mLocationHelper.updatesRequested()){
				loading();
				Location location = mLocationHelper.getLocation();
				if(location != null){
					locationUpdate(location);
				}else{
					mLocationHelper.requestUpdates(new LocationHelperListener() {
						
						@Override
						public void update(Location position) {
							locationUpdate(position);
						}
					});
				}	
			}
		}else{
			LatLng position = mMarker.getPosition();
			((ShopsActivity) getActivity()).nextFragment(position.latitude, position.longitude);
		}
	}
	
	@OnActivityResult(LocationHelper.ERROR_RESULT_CODE)
	protected void onResult(int resultCode){
		switch (resultCode) {
		case Activity.RESULT_OK: // Google Play Solved Error
			//enable();
			break;
		default: // Google Play Fatal Error Scenario
			error();
			break;
		}
	}
}
