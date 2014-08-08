package edu.mit.lastmile.km2.activity;

import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.fragment.delivery.DeliveryTrackingStartFragment_;

@EActivity(R.layout.activity_delivery_tracking_shop)
public class DeliveryTrackingShopActivity extends Activity {

	private int mBlockId;
	private int mStreetId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new DeliveryTrackingStartFragment_()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
	
    public void replaceFragment(Fragment fragment){
    	getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

	public int getmBlockId() {
		return mBlockId;
	}

	public void setmBlockId(int mBlockId) {
		this.mBlockId = mBlockId;
	}

	public int getmStreetId() {
		return mStreetId;
	}

	public void setmStreetId(int mStreetId) {
		this.mStreetId = mStreetId;
	}

}
