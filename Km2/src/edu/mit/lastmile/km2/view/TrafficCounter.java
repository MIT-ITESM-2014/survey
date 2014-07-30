package edu.mit.lastmile.km2.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.mit.lastmile.km2.R;

@EViewGroup(R.layout.traffic_counter)
public class TrafficCounter extends LinearLayout {

	private static final int WEIGHT_SUM = 2;
	
	private int mCount = 0;
	
	@ViewById
	protected TextView label, counter;
	
	@ViewById
	protected ImageView icon;
	
	@ViewById
	protected ImageButton subtractBtn, addBtn;
	
	public TrafficCounter(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@AfterViews
	protected void init(){
		setWeightSum(WEIGHT_SUM);
		updateCounter();
	}
	
	public void setUp(int icon, int label){ 
		this.icon.setImageResource(icon);
		this.label.setText(label);
	}
	
	public int getData(){
		return mCount;
	}
	
	@Click
	protected void subtractBtn(){
		if(mCount > 0){
			--mCount;
			updateCounter();
		}
	}
	
	@Click
	protected void addBtn(){
		++mCount;
		updateCounter();
	}
	
	private void updateCounter(){
		counter.setText("" + mCount);
	}

}
