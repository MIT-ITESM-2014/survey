package edu.mit.lastmile.km2.fragment.shops;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
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
import edu.mit.lastmile.km2.activity.ShopsActivity_;
import edu.mit.lastmile.km2.model.Shop;

@EFragment(R.layout.fragment_shops_list)
@OptionsMenu(R.menu.root)
public class ShopsListFragment extends ListFragment {
	
	public static final int SECTION = 2;
	
	@StringRes
	protected String shopsNoData;
	
	@ColorRes
	protected int shops;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((RootActivity) activity).onSectionAttached(SECTION);
	}

	@AfterViews
	protected void init(){
		initActionBar();
		//bindAdapter();
	}
	
	private void initActionBar(){
		ColorDrawable c = new ColorDrawable(shops);
		getActivity().getActionBar().setBackgroundDrawable(c);		
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