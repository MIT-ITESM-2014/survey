package edu.mit.lastmile.km2.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.fragment.NavigationDrawerFragment;
import edu.mit.lastmile.km2.fragment.root.MainMenuFragment_;
import edu.mit.lastmile.km2.fragment.shops.ShopsListFragment;
import edu.mit.lastmile.km2.fragment.shops.ShopsListFragment_;
import edu.mit.lastmile.km2.fragment.traffic.TrafficListFragment;
import edu.mit.lastmile.km2.fragment.traffic.TrafficListFragment_;

@EActivity(R.layout.activity_root)
public class RootActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

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
			default:
				replaceFragment(new MainMenuFragment_());
				break;
		}
    }
    
    public void replaceFragment(Fragment fragment){
    	getFragmentManager().beginTransaction()
    		.replace(R.id.container, fragment)
    		.commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
			case TrafficListFragment.SECTION:
				mTitle = getString(R.string.traffic_title);
				break;
			case ShopsListFragment.SECTION:
				mTitle = getString(R.string.shops_title);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!mNavigationDrawerFragment.isDrawerOpen()) {
        	/*switch (mSection) {
			case TrafficListFragment.SECTION:
				setNewActionMenu(menu);
				break;

			default:
				
				break;
			}
            return true;*/
        }
        return super.onCreateOptionsMenu(menu);
    }
    
    /*private void setNewActionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.root, menu);
        restoreActionBar();
    }*/
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
