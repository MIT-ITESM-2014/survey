package edu.mit.lastmile.km2.activity;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import edu.mit.lastmile.km2.R;

public class BaseActivity extends Activity {

	public void setActionBarColor(int resource){
		ColorDrawable c = new ColorDrawable(resource);
		getActionBar().setBackgroundDrawable(c);
	}
	
	public void setFragment(Fragment fragment){
		getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
	}

    public void replaceFragment(Fragment fragment){
    	getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
	
}
