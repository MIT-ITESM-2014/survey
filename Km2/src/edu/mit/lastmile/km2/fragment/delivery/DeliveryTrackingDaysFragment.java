package edu.mit.lastmile.km2.fragment.delivery;

import java.text.ParseException;
import java.util.ArrayList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.res.ColorRes;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.activity.DeliveryTrackingShopActivity_;
import edu.mit.lastmile.km2.activity.RootActivity;
import edu.mit.lastmile.km2.dao.DeliveryShopDataSource;
import edu.mit.lastmile.km2.model.DeliveryShop;

@EFragment
public class DeliveryTrackingDaysFragment extends Fragment {

	public static final int SECTION = 5;
	
	@ColorRes
	protected int deliveryTracking;
	
	@Bean
	protected DeliveryShopDataSource dao;

	@Bean
	protected App app;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((RootActivity) activity).onSectionAttached(SECTION);
	}
	
	/*@Override
	public void onStart() {
		super.onStart();
		init();
	}*/
	
	@AfterInject
	protected void init(){
		dao.open();
		try {
			ArrayList<DeliveryShop> items = dao.getElements();
			if(items.isEmpty()){
				Log.d(Config.LOG_TAG, "items empty()");
				chooseShops();
			}else{
				// TODO DisplayDayList
			}
		} catch (ParseException e) {
			app.databaseError();
		}finally{
			dao.close();
		}
	}
	
	@AfterViews
	protected void initViews(){
		initActionBar();
	}
	
	private void initActionBar(){
		ColorDrawable c = new ColorDrawable(deliveryTracking);
		getActivity().getActionBar().setBackgroundDrawable(c);		
	}
	
	private void chooseShops(){
		App.showToast(getActivity(), "Todavia no sirve :(");
		Intent intent = new Intent(getActivity(), DeliveryTrackingShopActivity_.class);
		startActivity(intent);
	}
	
}
