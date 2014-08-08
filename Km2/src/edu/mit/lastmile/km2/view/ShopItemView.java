package edu.mit.lastmile.km2.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DimensionRes;

import edu.mit.lastmile.km2.Config;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.model.Shop;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

@EViewGroup(R.layout.shop_item_view)
public class ShopItemView extends LinearLayout {

	@DimensionRes(R.dimen.shops_item_padding)
	protected float padding;
	
	@ViewById
	protected TextView shopName, shopType;
	
	public ShopItemView(Context context) {
		super(context);
	}
	
	@AfterViews
	protected void init(){
		int padding = (int) this.padding;
		setOrientation(VERTICAL);
		setPadding(padding * 2, padding, padding, padding);
	}
	
	public void bind(Shop item){
		Log.d(Config.LOG_TAG, item.toString());
		shopName.setText(item.getName());
		shopType.setText(item.getShopType());
	}

}
