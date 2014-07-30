package edu.mit.lastmile.km2.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.res.ColorRes;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import edu.mit.lastmile.km2.R;

@EActivity(R.layout.activity_shops)
public class ShopsActivity extends Activity {

	@ColorRes
	protected int shops;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			//getFragmentManager().beginTransaction().add(R.id.container, new LoginFragment_()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
	
	@AfterViews
	protected void init(){
		initActionBar();
	}
	
	private void initActionBar(){
		ColorDrawable c = new ColorDrawable(shops);
		getActionBar().setBackgroundDrawable(c);		
	}

}
