package edu.mit.lastmile.km2.view;

import android.content.Context;
import android.widget.LinearLayout;
import edu.mit.lastmile.km2.model.NavigationItem;

public abstract class NavigationItemView extends LinearLayout {

	public NavigationItemView(Context context) {
		super(context);
	}
	
	public abstract void bind(NavigationItem item);

}
