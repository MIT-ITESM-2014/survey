package edu.mit.lastmile.km2.fragment.shops;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.RootActivity;
import edu.mit.lastmile.km2.activity.ShopsActivity_;
import edu.mit.lastmile.km2.adapter.ShopListAdapter;
import edu.mit.lastmile.km2.model.Shop;

@EFragment(R.layout.fragment_shops_list)
@OptionsMenu(R.menu.root)
public class ShopsListFragment extends ListFragment {
	
	public static final int SECTION = 2;
	
	@StringRes
	protected String shopsNoData;
	
	@ColorRes
	protected int shops;
	
	@Bean
	protected ShopListAdapter adapter;
	
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
		((RootActivity) getActivity()).setActionBarColor(shops);
	}
	
	private void refresh(){
		adapter.setData();
		setListAdapter(adapter);
	}
	
	private void bindAdapter(){
		setEmptyText(shopsNoData);
		setListAdapter(adapter);
	}
	
	@OptionsItem
	protected void actionNewSelected(){
		Intent intent = new  Intent(getActivity(), ShopsActivity_.class);
		startActivity(intent);
	}
	
	@ItemClick
	protected void listItemClicked(Shop shop){
		// TODO
	}
	
}