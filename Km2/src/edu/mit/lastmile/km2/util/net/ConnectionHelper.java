package edu.mit.lastmile.km2.util.net;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SystemService;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@EBean
public class ConnectionHelper {

	private final static String WIFI = "WIFI";
	private final static String MOBILE = "MOBILE";
	
	@SystemService
	protected ConnectivityManager mManager;
	
	public boolean isConnected(){
		boolean haveWifi = false;
		boolean haveData = false;
		NetworkInfo[] netInfo = mManager.getAllNetworkInfo();
		for(NetworkInfo ni : netInfo){
			if(ni.getTypeName().equalsIgnoreCase(WIFI)){
				haveWifi = ni.isConnected();
			}
			if(ni.getTypeName().equalsIgnoreCase(MOBILE)){
				haveData = ni.isConnected();
			}
		}
		return haveWifi || haveData;
	}
	
}
