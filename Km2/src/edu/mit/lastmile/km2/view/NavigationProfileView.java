package edu.mit.lastmile.km2.view;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.model.NavigationItem;

import android.content.Context;
import android.widget.TextView;

@EViewGroup(R.layout.navigation_profile_item)
public class NavigationProfileView extends NavigationItemView {

	@ViewById
	TextView userName;
	
	@ViewById
	TextView kmName;
	
	@ViewById
	TextView location;
	
	public NavigationProfileView(Context context) {
		super(context);
	}
	
	@Override
	public void bind(NavigationItem item){
		userName.setText(item.getName());
		kmName.setText(item.getKm2());
		location.setText(item.getLocation());
	}

}