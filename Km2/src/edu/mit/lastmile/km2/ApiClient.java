package edu.mit.lastmile.km2;

import java.util.HashMap;
import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import edu.mit.lastmile.km2.model.Token;
import edu.mit.lastmile.km2.util.net.ServerRequest;
import edu.mit.lastmile.km2.util.net.ServerResponse;

@EBean(scope = Scope.Singleton)
public class ApiClient {

	public static final class Header {
		public static final String AuthToken = "Auth-Token";
		public static final String AuthSecret = "Auth-Secret";
	}	
	
	public static final class StatusCode{
		public static final int AUTH_ERROR = 401;
	}
	
	@Bean
	protected ServerRequest request;
	
	@Bean
	protected App app;
	
	@AfterInject
	protected void initServerRequest(){
		String endpoint = Config.Server.ENDPOINT + Config.Server.CONTROLLER;
		this.request.setEndpoint(endpoint);
	}
	
	public ServerRequest getRequest() {
		return request;
	}

	public void setRequest(ServerRequest request) {
		this.request = request;
	}
	
	public HashMap<String, String> getHeaders(){
		HashMap<String, String> headers = new HashMap<String, String>();
		Token token = app.getAuthToken();
		if(token != null){
			headers.put(Header.AuthToken, token.getToken());
			headers.put(Header.AuthSecret, token.getSecret());
		}
		return headers;
	}
	
	public void post(String path, List<NameValuePair> params, ApiClientResponse responseHandler){
		new PostApiHandler(path, params, responseHandler);
	}
	
	private final class PostApiHandler implements ServerResponse{

		private ApiClientResponse responseHandler;
		private List<NameValuePair> params;
		private String path;
		
		public PostApiHandler(String path, List<NameValuePair> params, ApiClientResponse responseHandler){
			setResponseHandler(responseHandler);
			setPath(path);
			setParams(params);
			request();
		}
		
		public ApiClientResponse getResponseHandler() {
			return responseHandler;
		}

		public void setResponseHandler(ApiClientResponse responseHandler) {
			this.responseHandler = responseHandler;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public List<NameValuePair> getParams() {
			return params;
		}

		public void setParams(List<NameValuePair> params) {
			this.params = params;
		}
			
		private void request(){
			getResponseHandler().onLoading();
			getRequest().post(getPath(), getParams(), getHeaders(), this);
		}
		
		@Override
		public void onSuccess(String rawResponse) {
			try{
				JSONObject response = new JSONObject(rawResponse);
				getResponseHandler().onLoaded();
				getResponseHandler().onSuccess(response);
			}
			catch(JSONException e){
				onError(ServerRequest.ERROR_STATUS);
			}		
		}

		@Override
		public void onError(int statusCode) {
			Activity activity = getResponseHandler().getActivity();
			getResponseHandler().onLoaded();
			if(statusCode == StatusCode.AUTH_ERROR){
				app.authError(activity);
			}
			else{
				getResponseHandler().onError();
			}
		}
		
	}
	
}