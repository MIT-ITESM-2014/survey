package edu.mit.lastmile.km2.adapter;

import java.text.ParseException;
import java.util.ArrayList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.dao.TrafficCountDataSource;
import edu.mit.lastmile.km2.model.TrafficCount;

@EBean
public class TrafficCountListAdapter extends BaseAdapter {
	
	private ArrayList<TrafficCount> mItems;
	
	@RootContext
	protected Context mContext;
	
	@Bean
	protected TrafficCountDataSource dao;
	
	@Bean
	protected App app;

	@AfterInject
	public void setData(){
		dao.open();
		try {
			mItems = dao.getElements();
		} catch (ParseException e) {
			app.databaseError(mContext);
		}finally{
			dao.close();
		}
	}
	
	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public long getItemId(int position) {
		return mItems.get(position).getId();
	}
	
	@Override
	public TrafficCount getItem(int position) {
		return mItems.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		TrafficCount tc = getItem(position);
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
		    holder = new ViewHolder();
		    holder.text = (TextView) convertView.findViewById(android.R.id.text1);
		    convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(tc.getStartedAt().toString());
		return convertView;
	}
	
	private static class ViewHolder{
		TextView text;
	}
	
}
