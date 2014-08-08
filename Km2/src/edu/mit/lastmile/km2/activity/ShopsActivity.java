package edu.mit.lastmile.km2.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.res.ColorRes;

import android.os.Bundle;
import android.view.Menu;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.fragment.shops.ShopFormFragment_;
import edu.mit.lastmile.km2.fragment.shops.ShopMapFragment_;

@EActivity(R.layout.activity_shops)
public class ShopsActivity extends BaseActivity {

	private double lat;
	private double lng;
	
	@ColorRes
	protected int shops;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new ShopMapFragment_()).commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
	
	@AfterViews
	protected void init(){
		setActionBarColor(shops);
	}
	
	public void nextFragment(double lat, double lng){
		this.lat = lat;
		this.lng = lng;
		replaceFragment(new ShopFormFragment_());
	}

    public double getLat(){
    	return lat;
    }
    
    public double getLng(){
    	return lng;
    }
    
}
