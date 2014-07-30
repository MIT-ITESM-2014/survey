package edu.mit.lastmile.km2.view;

import org.androidannotations.annotations.EViewGroup;

import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.model.NavigationItem;

import android.content.Context;

@EViewGroup(R.layout.navigation_separator_item)
public class NavigationSeparatorView extends NavigationItemView {

	public NavigationSeparatorView(Context context) {
		super(context);
	}

	@Override
	public void bind(NavigationItem item) {
		// Empty no views to set
	}

}
