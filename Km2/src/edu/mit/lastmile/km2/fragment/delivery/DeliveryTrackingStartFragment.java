package edu.mit.lastmile.km2.fragment.delivery;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.widget.Spinner;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.DeliveryTrackingShopActivity;

@EFragment(R.layout.fragment_delivery_tracking_start)
@OptionsMenu(R.menu.delivery_tracking)
public class DeliveryTrackingStartFragment extends Fragment {

	@ColorRes
	protected int deliveryTracking;
	
	@ViewById
	protected Spinner blockIdField, streetIdField;
	
	@AfterViews
	protected void init(){
		initActionBar();
		//bindAdapter();
	}
	
	private void initActionBar(){
		ColorDrawable c = new ColorDrawable(deliveryTracking);
		getActivity().getActionBar().setBackgroundDrawable(c);		
	}
	
	@Click
	protected void submitBtn(){
		setData();
		((DeliveryTrackingShopActivity) getActivity()).replaceFragment(new DeliveryTrackingShopSelectFragment_());
	}
	
	private int spinnerToInt(Spinner s){
		if(s.getSelectedItem() == null){
			return -1;
		}else{
			return Integer.parseInt((String) s.getSelectedItem());
		}
	}
	
	private void setData(){
		((DeliveryTrackingShopActivity) getActivity()).setmBlockId(spinnerToInt(blockIdField));
		((DeliveryTrackingShopActivity) getActivity()).setmStreetId(spinnerToInt(streetIdField));
	}
	
}
