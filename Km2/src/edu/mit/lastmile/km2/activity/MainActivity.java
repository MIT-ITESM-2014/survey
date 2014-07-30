package edu.mit.lastmile.km2.activity;


import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.fragment.LoginFragment_;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new LoginFragment_()).commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

}
