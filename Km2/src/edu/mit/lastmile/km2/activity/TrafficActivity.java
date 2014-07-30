package edu.mit.lastmile.km2.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.dao.TrafficCountDataSource;
import edu.mit.lastmile.km2.model.TrafficCount;
import edu.mit.lastmile.km2.view.TrafficCounter;

@EActivity(R.layout.activity_traffic)
public class TrafficActivity extends Activity {
	
	private static final int START = 0;
	private static final int IN_PROGRESS = 1;
	private static final int FINISHED = 2;
	private static final long START_TIME = 10 * 1000; // 45 minutes
	private static final long INTERVAL = 1 * 1000; // Per second
	private int mStatus = START;
	private TrafficTimer mTimer;
	
	@ColorRes
	protected int traffic;
	
	@StringRes
	protected String trafficStartBtn, trafficSaveBtn, trafficSubmitBtn;
	
	@ViewById
	protected TrafficCounter 
				cars, nonMotorized, motorized, pickup,
				smallTruck, mediumTruck, largeTruck,
				others;
	
	@ViewById
	protected Button actionBtn;
	
	@ViewById
	protected TextView countdown;
	
	@Bean
	App app;
	
	@Bean
	TrafficCountDataSource dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//TODO init with bundle
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
	
	@Override
	public void onBackPressed() {
		if(mTimer != null){
			mTimer.cancel();
		}
		// TODO save if in progress
		super.onBackPressed();
	}
	
	@AfterViews
	protected void init(){
		initView();
		initActionBar();
		initCounters();
	}
	
	private void initView(){
		actionBtn.setText(trafficStartBtn);
		updateCountdown(START_TIME);
	}
	
	private void initActionBar(){
		ColorDrawable c = new ColorDrawable(traffic);
		getActionBar().setBackgroundDrawable(c);		
	}
	
	private void initCounters(){
		cars.setUp(R.drawable.ic_traffic_car, R.string.traffic_car);
		nonMotorized.setUp(R.drawable.ic_traffic_non_motorized_wheeler, R.string.traffic_non_motorized_wheeler);
		motorized.setUp(R.drawable.ic_traffic_motorized_wheeler, R.string.traffic_motorized_wheeler);
		pickup.setUp(R.drawable.ic_traffic_pickup, R.string.traffic_pickup);
		smallTruck.setUp(R.drawable.ic_traffic_small_truck, R.string.traffic_small_truck);
		mediumTruck.setUp(R.drawable.ic_traffic_medium_truck, R.string.traffic_medium_truck);
		largeTruck.setUp(R.drawable.ic_traffic_large_truck, R.string.traffic_large_truck);
		others.setUp(R.drawable.ic_traffic_other, R.string.traffic_other);
	}
	
	@Click
	protected void actionBtn(){
		switch(mStatus){
		case START:
			startState();
			break;
		case IN_PROGRESS:
			inProgressState();
			break;
		case FINISHED:
			saveData();
			break;
		default:
			break;
		}
	}
	
	private void startState(){
		mStatus = IN_PROGRESS;
		actionBtn.setText(trafficSaveBtn);
		mTimer = new TrafficTimer(START_TIME, INTERVAL);
		mTimer.start();
	}
	
	private void inProgressState(){
		// TODO remove
		//TODO save progress
	}
	
	private void finishState(){
		mStatus = FINISHED;
		actionBtn.setText(trafficSubmitBtn);
		updateCountdown(0);
	}
	
	private void saveData(){
		saveDb();
		finish();
	}
	
	private void saveDb(){
		TrafficCount element = getData();
		dao.open();
		if(dao.insert(element) == -1){
			app.databaseError(this);
		}else{
			app.databaseSuccess(this);
		}
		dao.close();		
	}

	private TrafficCount getData(){
		TrafficCount model = new TrafficCount();
		model.setCars(cars.getData());
		model.setBikes(nonMotorized.getData());
		model.setMotorbikes(motorized.getData());
		model.setPickupTrucks(pickup.getData());
		model.setVans(smallTruck.getData());
		model.setRigidTrucks(mediumTruck.getData());
		model.setArticulatedTrucks(largeTruck.getData());
		model.setPedestrians(others.getData());
		model.setStatus(mStatus);
		return model;
	}
	
	private void updateCountdown(long millis){
		String s = formatTime(millis);
		countdown.setText(s);
	}
	
	private String formatTime(long millis){
		long secs = millis / 1000;
		long min = secs / 60;
		secs = secs % 60;
		min = min % 60;
		String seconds = String.valueOf(secs);
		String minutes = String.valueOf(min);
		if(secs < 10){
			seconds = "0" + seconds;
		}
		if(min < 10){
			minutes = "0" + minutes;
		}
		return minutes + ":" + seconds;
	}
	
	private class TrafficTimer extends CountDownTimer {

		public TrafficTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			updateCountdown(millisUntilFinished);
		}

		@Override
		public void onFinish() {
			finishState();
		}
		
	}
	
}
