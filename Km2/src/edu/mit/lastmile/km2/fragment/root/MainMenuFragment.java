package edu.mit.lastmile.km2.fragment.root;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import android.app.Activity;
import android.app.Fragment;
import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.RootActivity;
import edu.mit.lastmile.km2.fragment.SupportFragment;
import edu.mit.lastmile.km2.fragment.delivery.DeliveryTrackingDaysFragment_;
import edu.mit.lastmile.km2.fragment.shops.ShopsListFragment_;
import edu.mit.lastmile.km2.fragment.summary.SummaryFragment_;
import edu.mit.lastmile.km2.fragment.traffic.TrafficListFragment_;

@EFragment(R.layout.fragment_main_menu)
@OptionsMenu(R.menu.global)
public class MainMenuFragment extends SupportFragment {
	
	public static final int SECTION = 0;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((RootActivity) activity).onSectionAttached(SECTION);
	}
	
	@AfterViews
	protected void init(){
		initActionBar();
	}
	
	@Click
	protected void trafficBtn(){
		moveTo(new TrafficListFragment_());
	}
	
	@Click
	protected void shopsBtn(){
		moveTo(new ShopsListFragment_());
	}
	
	@Click
	protected void roadsAndRegulationsBtn(){
		notReady();
	}
	
	@Click
	protected void obsructionsBtn(){
		notReady();
	}
	
	@Click
	protected void deliveryTrackingBtn(){
		//moveTo(new DeliveryTrackingDaysFragment_());
		notReady();
	}
	
	@Click
	protected void uploadBtn(){
		moveTo(new SummaryFragment_());
	}
	
	@OptionsItem
	protected void actionLogout(){
		mApp.logout(getActivity());
	}
	
	private void notReady(){
		App.showToast(getActivity(), "Unavailable");
	}
	
	private void moveTo(Fragment fragment){
		RootActivity activity = (RootActivity) getActivity();
		activity.replaceFragment(fragment);
	}
	
}