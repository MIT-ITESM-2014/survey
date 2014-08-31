package edu.mit.lastmile.km2.fragment.summary;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import android.app.Activity;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.RootActivity;
import edu.mit.lastmile.km2.fragment.SupportFragment;

@EFragment(R.layout.fragment_summary)
public class SummaryFragment extends SupportFragment{

	public static final int SECTION = 6;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((RootActivity) activity).onSectionAttached(SECTION);
	}
	
	@AfterViews
	protected void init(){
		initActionBar();
	}
	
}
