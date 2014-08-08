package edu.mit.lastmile.km2.util.net;

public interface ServerResponse {
	void onSuccess(String response);
	void onError(int statusCode);
}
