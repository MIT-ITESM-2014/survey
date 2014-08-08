package edu.mit.lastmile.km2.activity;

import org.androidannotations.annotations.EActivity;

import android.os.Bundle;
import android.view.Menu;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.fragment.delivery.DeliveryTrackingShopSelectFragment_;

@EActivity(R.layout.activity_delivery_tracking)
public class DeliveryTrackingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			setFragment(new DeliveryTrackingShopSelectFragment_());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
}
