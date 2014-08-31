package edu.mit.lastmile.km2.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.fragment.NavigationDrawerFragment;
import edu.mit.lastmile.km2.fragment.delivery.DeliveryTrackingDaysFragment;
import edu.mit.lastmile.km2.fragment.delivery.DeliveryTrackingDaysFragment_;
import edu.mit.lastmile.km2.fragment.root.MainMenuFragment_;
import edu.mit.lastmile.km2.fragment.shops.ShopsListFragment;
import edu.mit.lastmile.km2.fragment.shops.ShopsListFragment_;
import edu.mit.lastmile.km2.fragment.summary.SummaryFragment;
import edu.mit.lastmile.km2.fragment.summary.SummaryFragment_;
import edu.mit.lastmile.km2.fragment.traffic.TrafficListFragment;
import edu.mit.lastmile.km2.fragment.traffic.TrafficListFragment_;

@EActivity(R.layout.activity_root)
public class RootActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    @FragmentById(R.id.navigation_drawer)
	protected NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    /**
     * Used to store currentSection
     */
    //private int mSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getTitle();
    }
    
    @AfterViews
    protected void initNavigation(){
    	mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        switch (position) {
			case TrafficListFragment.SECTION:
				replaceFragment(new TrafficListFragment_());
				break;
			case ShopsListFragment.SECTION:
				replaceFragment(new ShopsListFragment_());
				break;
			case DeliveryTrackingDaysFragment.SECTION:
				replaceFragment(new DeliveryTrackingDaysFragment_());
			case SummaryFragment.SECTION:
				replaceFragment(new SummaryFragment_());
			default:
				replaceFragment(new MainMenuFragment_());
				break;
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            //getMenuInflater().inflate(R.menu.global, menu);
            restoreActionBar();
            return false;
        }
        return false;
        //return super.onCreateOptionsMenu(menu);
    }

    public void onSectionAttached(int number) {
        switch (number) {
			case TrafficListFragment.SECTION:
				mTitle = getString(R.string.traffic_title);
				break;
			case ShopsListFragment.SECTION:
				mTitle = getString(R.string.shops_title);
				break;
			case DeliveryTrackingDaysFragment.SECTION:
				mTitle = getString(R.string.delivery_tracking_btn);
			case SummaryFragment.SECTION:
				mTitle = getString(R.string.summary_title);
				break;
			default:
				mTitle = getString(R.string.app_name);
				break;
		}
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

}
