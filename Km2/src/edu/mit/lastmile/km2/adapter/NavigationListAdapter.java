package edu.mit.lastmile.km2.adapter;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.res.StringArrayRes;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.model.NavigationItem;
import edu.mit.lastmile.km2.view.NavigationItemView;
import edu.mit.lastmile.km2.view.NavigationMenuView_;
import edu.mit.lastmile.km2.view.NavigationProfileView_;
import edu.mit.lastmile.km2.view.NavigationSeparatorView_;

@EBean
public class NavigationListAdapter extends BaseAdapter {

	@RootContext
	protected Context mContext;
	
    @StringArrayRes(R.array.navigation_items)
    protected String[] mStrArray;
    
    @StringArrayRes(R.array.navigation_items_extra)
    protected String[] mExtraArray;
    
    // No annotation available for resource
    protected TypedArray mIconArray;
	protected TypedArray mIconExtraArray;
    
	private ArrayList<NavigationItem> items;
	
    @AfterInject
    void initItems(){
    	int length = mStrArray.length;
    	items = new ArrayList<NavigationItem>();
    	items.add(new NavigationItem("Gury", "Km2", "Beijing China"));
    	mIconArray = mContext.getResources().obtainTypedArray(R.array.navigation_icons);
    	mIconExtraArray = mContext.getResources().obtainTypedArray(R.array.navigation_icons_extra);
    	for(int i = 0; i < length; ++i){
    		NavigationItem item = new NavigationItem(mIconArray.getResourceId(i, -1), mStrArray[i]);
    		items.add(item);
    	}
    	items.add(new NavigationItem()); // Separator
    	mIconArray.recycle();
    	length = mExtraArray.length;
    	for(int i = 0; i < length; ++i){
    		NavigationItem item = new NavigationItem(mIconExtraArray.getResourceId(i, -1), mExtraArray[i]);
    		items.add(item);
    	}
    	mIconExtraArray.recycle();
    }
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public NavigationItem getItem(int index) {
		return items.get(index);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		NavigationItemView view;
		NavigationItem item = getItem(index);
		int type = item.getType();
		if(convertView == null){
			if(type == NavigationItem.NAVIGATION_PROFILE){
				view = NavigationProfileView_.build(mContext);
			}else if(type == NavigationItem.NAVIGATION_MENU){
				view = NavigationMenuView_.build(mContext);
			}else{
				view = NavigationSeparatorView_.build(mContext);
			}
		}else{
			view = (NavigationItemView) convertView;
		}
		// View holder pattern replaced by dependency injection
		if(type == NavigationItem.NAVIGATION_PROFILE){
			((NavigationProfileView_) view).bind(item);
		}else if(type == NavigationItem.NAVIGATION_MENU){
			((NavigationMenuView_) view).bind(item);
		}else{
			((NavigationSeparatorView_) view).bind(item);
		}
		return view;
	}

}
