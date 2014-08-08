package edu.mit.lastmile.km2.activity;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import edu.mit.lastmile.km2.App;
import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.dao.KmDataSource;
import edu.mit.lastmile.km2.dao.TokenDataSource;
import edu.mit.lastmile.km2.fragment.LoginFragment_;
import edu.mit.lastmile.km2.model.Km;
import edu.mit.lastmile.km2.model.Token;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

	@Bean
	protected App app;
	
	@Bean
	protected TokenDataSource dao;
	
	@Bean
	protected KmDataSource kDao;
	
	private Bundle savedInstance; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		savedInstance = savedInstanceState;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		init();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getActionBar().hide();
		return false;
	}
	
	private void init(){
		initAuthToken();
		if(savedInstance == null){
			if(app.hasSession()){
				app.startRootActivity(this);
			}else{
				setFragment(new LoginFragment_());
			}
		}
	}
	
	private void initAuthToken(){
		try{
			dao.open();
			Token authToken = dao.getToken();
			if(authToken == null){
				app.setAuthToken(null);
			}else{
				app.setAuthToken(authToken);
				initKm();
			}
		}catch (SQLException e) {
			app.databaseError();
		} finally {
			dao.close();
		}
	}
	
	private void initKm(){
		try{
			kDao.open();
			Km km = kDao.getKm();
			if(km == null){ 
				// No assigned km
				app.setAuthToken(null);
				app.setUserKm(null);
			}else{
				app.setUserKm(km);
			}
		}catch (SQLException e) {
			app.databaseError();
		} finally {
			kDao.close();
		}
	}

}
