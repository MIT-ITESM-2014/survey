package edu.mit.lastmile.km2.fragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.res.ColorRes;

import edu.mit.lastmile.km2.App;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;

@EBean
public class SupportFragment extends Fragment {

	@Bean
	protected App mApp;
	
	@ColorRes
	protected int support;
	
	protected void initActionBar(){
		ColorDrawable c = new ColorDrawable(support);
		getActivity().getActionBar().setBackgroundDrawable(c);		
	}
	
}
