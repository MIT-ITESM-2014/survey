package edu.mit.lastmile.km2.fragment.traffic;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.RootActivity;
import edu.mit.lastmile.km2.activity.TrafficActivity_;
import edu.mit.lastmile.km2.adapter.TrafficCountListAdapter;

@EFragment(R.layout.fragment_traffic_list)
@OptionsMenu(R.menu.root)
public class TrafficListFragment extends ListFragment {

	public static final int SECTION = 1;
	
	@StringRes
	protected String trafficNoData;
	
	@ColorRes
	protected int traffic;
	
	@Bean
	protected TrafficCountListAdapter adapter;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((RootActivity) activity).onSectionAttached(SECTION);
	}
	
	@Override
	public void onResume() {
		refresh();
		super.onResume();
	}
	
	@AfterViews
	protected void init(){
		initActionBar();
		bindAdapter();
	}
	
	private void initActionBar(){
		ColorDrawable c = new ColorDrawable(traffic);
		getActivity().getActionBar().setBackgroundDrawable(c);		
	}
	
	private void refresh(){
		adapter.setData();
		setListAdapter(adapter);
	}
	
	private void bindAdapter(){
		setEmptyText(trafficNoData);
		setListAdapter(adapter);
	}
	
	@OptionsItem
	protected void actionNewSelected(){
		Intent intent = new  Intent(getActivity(), TrafficActivity_.class);
		startActivity(intent);
	}
	
}
