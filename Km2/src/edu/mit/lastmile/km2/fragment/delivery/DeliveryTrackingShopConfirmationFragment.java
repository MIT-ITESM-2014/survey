package edu.mit.lastmile.km2.fragment.delivery;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import android.app.ListFragment;
import android.widget.Button;
import edu.mit.lastmile.km2.R;

@EFragment(R.layout.fragment_delivery_tracking_shop_confirmation)
public class DeliveryTrackingShopConfirmationFragment extends ListFragment {
	
	
	@AfterViews
	protected void initViews(){
		setListAdapter(null);
		initContinueBtn();
		//getListView().setAdapter(new ArrayAdapter<T>(getActivity(),));
	}
	
	private void initContinueBtn(){
		Button b = new Button(getActivity(), null, R.style.ActionButton);
		getListView().addFooterView(b);
	}
	
}
