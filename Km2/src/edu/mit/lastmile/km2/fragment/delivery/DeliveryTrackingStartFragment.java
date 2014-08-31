package edu.mit.lastmile.km2.fragment.delivery;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import android.app.Fragment;
import android.database.SQLException;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.DeliveryTrackingShopActivity;
import edu.mit.lastmile.km2.dao.BlockDataSource;
import edu.mit.lastmile.km2.dao.StreetDataSource;
import edu.mit.lastmile.km2.model.Block;
import edu.mit.lastmile.km2.model.Street;

@EFragment(R.layout.fragment_delivery_tracking_start)
@OptionsMenu(R.menu.delivery_tracking)
public class DeliveryTrackingStartFragment extends Fragment {

	@ColorRes
	protected int deliveryTracking;
	
	@ViewById
	protected Spinner blockIdField, streetIdField;
	
	@Bean
	protected BlockDataSource bDao;
	
	@Bean
	protected StreetDataSource sDao;
	
	@Bean
	protected App mApp;
	
	@AfterViews
	protected void init(){
		initActionBar();
		initSpinners();
	}
	
	private void initActionBar(){
		ColorDrawable c = new ColorDrawable(deliveryTracking);
		getActivity().getActionBar().setBackgroundDrawable(c);		
	}
	
	private void initSpinners(){
		initBlockField();
	}
	
	private void initBlockField(){
		//TODO exception validation
		bDao.open();
		ArrayList<Block> objects = bDao.getElements();
		bDao.close();
		ArrayAdapter<Block> adapter = new ArrayAdapter<Block>(getActivity(), android.R.layout.simple_spinner_item, objects);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		blockIdField.setAdapter(adapter);
		blockIdField.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				onBlockIdFieldSelected(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO nothing
			}
		});
	}
	
	private void onBlockIdFieldSelected(int position){
		Block b = (Block) blockIdField.getItemAtPosition(position);
		try {
			sDao.open();
			ArrayList<Street> objects = sDao.findByBlock(b.getId());
			ArrayAdapter<Street> adapter = new ArrayAdapter<Street>(getActivity(), android.R.layout.simple_spinner_item, objects);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			streetIdField.setAdapter(adapter);
		} catch (SQLException e) {
			mApp.serverError();
		}finally{
			sDao.close();
		}
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
