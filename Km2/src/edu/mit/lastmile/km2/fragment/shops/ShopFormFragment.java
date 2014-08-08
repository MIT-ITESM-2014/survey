package edu.mit.lastmile.km2.fragment.shops;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.ShopsActivity;
import edu.mit.lastmile.km2.dao.ShopDataSource;
import edu.mit.lastmile.km2.model.Shop;
import edu.mit.lastmile.km2.util.Camera;

@EFragment(R.layout.fragment_shops_form)
public class ShopFormFragment extends Fragment implements ValidationListener {
	
	public static final int CAPTURE_IMAGE_REQUEST_CODE = 100;
	private boolean mHasPhoto = false;
	private Validator mValidator;
	private Uri mUri;
	
	@Bean
	protected Camera mCamera;
	
	@Bean
	protected App mApp;
	
	@Bean
	protected ShopDataSource dao;
	
	@Required(order = 1)
	@NumberRule(gt = 0, order = 2, type = NumberType.INTEGER)
	@ViewById
	protected EditText blockIdField;

	@Required(order = 3)
	@NumberRule(gt = 0, order = 4, type = NumberType.INTEGER)
	@ViewById
	protected EditText streetIdField;

	@Required(order = 5)
	@TextRule(order = 6, maxLength = 100)
	@ViewById
	protected EditText shopNameField;
	
	@Required(order = 7)
	@NumberRule(gt = 0, order = 8, type = NumberType.INTEGER)
	@ViewById
	protected EditText frontLengthField;
	
	@Required(order = 9)
	@NumberRule(gt = 0, order = 10, type = NumberType.INTEGER)
	@ViewById
	protected EditText totalFloorsField;
	
	@TextRule(order = 11, maxLength = 300)
	@ViewById
	protected EditText notesField;
	
	@ViewById
	protected Spinner shopTypeField, shopSizeField;
	
	@ViewById
	protected LinearLayout loadingAreaTypeLayout;
	
	@ViewById
	protected Switch loadingAreaField, loadingAreaTypeField;
	
	@ViewById
	protected Button uploadPhotoBtn;
	
	@AfterViews
	protected void initViews(){
		initValidation();
		initSpinners();
		initSwitches();
	}
	
	private void initSpinners(){
		initTypes();
		initSizes();
	}
	
	private void initSwitches(){
		loadingAreaField.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				loadingAreaFieldOnCheck(isChecked);
			}
		});
	}
	
	private void loadingAreaFieldOnCheck(boolean isChecked){
		if(isChecked){
			loadingAreaTypeLayout.setVisibility(Switch.VISIBLE);
		}else{
			loadingAreaTypeField.setChecked(false);
			loadingAreaTypeLayout.setVisibility(Switch.GONE);
		}
	}
	
	private void initTypes(){
		ArrayAdapter<CharSequence> sizes = ArrayAdapter.createFromResource(getActivity(), R.array.shops_shop_type, android.R.layout.simple_spinner_item);
		sizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		shopTypeField.setAdapter(sizes);
	}
	
	private void initSizes(){
		ArrayAdapter<CharSequence> sizes = ArrayAdapter.createFromResource(getActivity(), R.array.shops_shop_size, android.R.layout.simple_spinner_item);
		sizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		shopSizeField.setAdapter(sizes);
	}
	
	private void initValidation(){
		mValidator = new Validator(this);
		mValidator.setValidationListener(this);
	}
	
	@Click
	protected void uploadPhotoBtn(){
		launchCamera();
	}
	
	private void launchCamera(){
		if(!mHasPhoto){
			try {
				mCamera.launchForImage(this, CAPTURE_IMAGE_REQUEST_CODE);
			} catch (NullPointerException e) {
				mApp.camaraUnavailable();
			}
		}
	}
	
	@OnActivityResult(CAPTURE_IMAGE_REQUEST_CODE)
	protected void onResult(int resultCode){
		switch (resultCode) {
		case Activity.RESULT_OK:
			imageSaved();
			break;
		case Activity.RESULT_CANCELED:
			imageCancel();
			break;
		default:
			imageError();
			break;
		}	
	}
	
	private void imageSaved(){
		mHasPhoto = true;
		mUri = mCamera.getUri();
		uploadPhotoBtn.setText(R.string.shops_upload_photo_saved_btn);
	}
	
	private void imageCancel(){
		// Camera call canceled, no handling.
	}
	
	private void imageError(){
		mApp.camaraError();
	}
	
	@Click
	protected void saveBtn(){
		mValidator.validate();
	}

	@Override
	public void onValidationFailed(View view, Rule<?> rule) {
		String message = rule.getFailureMessage();
		if(view instanceof EditText){
			view.requestFocus();
			((EditText) view).setError(message);
		}else{
			mApp.toast(message);
		}
	}
	
	private String fieldToString(EditText field){
		return field.getText().toString();
	}
	
	private long fieldToLong(EditText field){
		return Long.parseLong(field.getText().toString());
	}
	
	private double fieldToDouble(EditText field){
		return Double.parseDouble(field.getText().toString());
	}
	
	private int fieldToInt(EditText field){
		return Integer.parseInt(field.getText().toString());
	}
	
	private int fieldBooleanInt(Switch field){
		if(field.isChecked()){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public void onValidationSucceeded() {
		saveDb();
		getActivity().finish();
	}
	
	private void saveDb(){
		Shop element = getData();
		dao.open();
		if(dao.insert(element) == -1){
			mApp.databaseError();
		}else{
			mApp.databaseSuccess();
		}
		dao.close();			
	}
	
	private String getSpinnerData(Spinner s){
		return (String) s.getSelectedItem();
	}
	
	private Shop getData(){
		ShopsActivity activity = (ShopsActivity) getActivity();
		Shop shop = new Shop();
		shop.setStreetId(fieldToLong(streetIdField));
		shop.setBlockId(fieldToLong(blockIdField));
		shop.setName(fieldToString(shopNameField));
		shop.setShopType(getSpinnerData(shopTypeField));
		shop.setShopType(getSpinnerData(shopSizeField));
		shop.setFrontLength(fieldToDouble(frontLengthField));
		shop.setTotalFloors(fieldToInt(totalFloorsField));
		shop.setHasLoadingArea(fieldBooleanInt(loadingAreaField));
		shop.setLoadingAreaType(fieldBooleanInt(loadingAreaTypeField));
		shop.setLat(activity.getLat());
		shop.setLng(activity.getLng());
		shop.setNotes(fieldToString(notesField));
		shop.setImage(mUri);
		return shop;
	}
}
