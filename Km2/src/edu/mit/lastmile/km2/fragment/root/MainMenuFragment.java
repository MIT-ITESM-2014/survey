package edu.mit.lastmile.km2.fragment.root;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

import android.app.Activity;
import android.app.Fragment;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.RootActivity;
import edu.mit.lastmile.km2.fragment.shops.ShopsListFragment_;
import edu.mit.lastmile.km2.fragment.traffic.TrafficListFragment_;

@EFragment(R.layout.fragment_main_menu)
@OptionsMenu(R.menu.global)
public class MainMenuFragment extends Fragment {
	
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
	
	private void initActionBar(){
		//ColorDrawable c = new ColorDrawable(traffic);
		//getActivity().getActionBar().setBackgroundDrawable(null);		
	}
	
	@Click
	protected void trafficBtn(){
		moveTo(new TrafficListFragment_());
	}
	
	@Click
	protected void shopsBtn(){
		moveTo(new ShopsListFragment_());
	}
	
	private void moveTo(Fragment fragment){
		RootActivity activity = (RootActivity) getActivity();
		activity.replaceFragment(fragment);
	}
	
}