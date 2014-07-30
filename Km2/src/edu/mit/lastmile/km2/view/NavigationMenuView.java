package edu.mit.lastmile.km2.view;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.model.NavigationItem;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

@EViewGroup(R.layout.navigation_menu_item)
public class NavigationMenuView extends NavigationItemView {

	@ViewById
	ImageView icon;
	
	@ViewById
	TextView text;
	
	public NavigationMenuView(Context context) {
		super(context);
	}

	@Override
	public void bind(NavigationItem item) {
		icon.setImageResource(item.getIcon());
		text.setText(item.getLabel());
	}
}
