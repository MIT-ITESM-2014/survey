package edu.mit.lastmile.km2;

import org.json.JSONObject;

import android.app.Activity;

public interface ApiClientResponse {

	Activity getActivity();
	void onLoading();
	void onLoaded();
	void onSuccess(JSONObject response);
	void onError();

}