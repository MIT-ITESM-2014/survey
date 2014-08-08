package edu.mit.lastmile.km2.fragment.summary;

import org.androidannotations.annotations.EFragment;

import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.RootActivity;
import android.app.Activity;
import android.app.Fragment;

@EFragment(R.layout.fragment_summary)
public class SummaryFragment extends Fragment {

	public static final int SECTION = 6;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((RootActivity) activity).onSectionAttached(SECTION);
	}
	
}
