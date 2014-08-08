package edu.mit.lastmile.km2.adapter;

import java.text.ParseException;
import java.util.ArrayList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.dao.ShopDataSource;
import edu.mit.lastmile.km2.model.Shop;
import edu.mit.lastmile.km2.view.ShopItemView;
import edu.mit.lastmile.km2.view.ShopItemView_;

@EBean
public class ShopListAdapter extends BaseAdapter {

	private ArrayList<Shop> mItems;
	
	@RootContext
	protected Context mContext;
	
	@Bean
	protected ShopDataSource dao;
	
	@Bean
	protected App app;
	
	@AfterInject
	public void setData(){
		dao.open();
		try {
			mItems = dao.getElements();
		} catch (ParseException e) {
			app.databaseError();
		}finally{
			dao.close();
		}
	}
	
	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Shop getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mItems.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ShopItemView view; // ViewHolder replacement
		if(convertView == null){
			view = ShopItemView_.build(mContext);
		}else{
			view = (ShopItemView) convertView;
		}
		view.bind(getItem(position));
		return view;
	}

}
