package edu.mit.lastmile.km2.util.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import edu.mit.lastmile.km2.Config;

@EBean
public class ServerRequest extends DefaultHttpClient{
	
	private String endpoint;
	private ServerResponse responseHandler;
	private List<NameValuePair> requestParams;
	private HashMap<String, String> requestHeaders;
	private HttpResponse currResponse;
	
	public static final int ERROR_STATUS = -1;

	
	public void post(String path, List<NameValuePair> params, HashMap<String, String> headers, ServerResponse responseHandler){
		this.responseHandler = responseHandler;
		this.requestParams = params;
		setRequestHeaders(headers);
		executePost(getPath(path));
	}
	
	@Background
	protected void executePost(String uri){
		HttpPost post = new HttpPost(uri);
		for(String key : getRequestHeaders().keySet()){
			post.addHeader(key, getRequestHeaders().get(key));
		}
		String response = "";
		try {
			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(requestParams);
			post.setEntity(ent);
			HttpResponse res = doExecute(post);
			int statusCode = res.getStatusLine().getStatusCode();
			if(statusCode == HttpStatus.SC_OK){
				response = EntityUtils.toString(res.getEntity());
				success(response);
			}
			else{
				error(statusCode);
			}
			res.getEntity().consumeContent();
			setCurrResponse(null);
		} catch (UnsupportedEncodingException e) {
			errorMsg(ERROR_STATUS, e.getLocalizedMessage());
		} catch (ClientProtocolException e) {
			errorMsg(ERROR_STATUS, e.getLocalizedMessage());
		} catch (IOException e) {
			errorMsg(ERROR_STATUS, e.getLocalizedMessage());
		}
	}
	
	@UiThread
	protected void success(String response){
		responseHandler.onSuccess(response);
	}
	
	@UiThread
	protected void error(int code){
		responseHandler.onError(code);
	}
	
	@UiThread
	protected void errorMsg(int code, String msg){
		Log.e(Config.LOG_TAG, msg);
		error(code);
	}

	private HttpResponse doExecute(HttpUriRequest req) throws ClientProtocolException, IOException{
		if(getCurrResponse() != null){
			getCurrResponse().getEntity().consumeContent();
			setCurrResponse(null);
		}
		HttpResponse res = execute(req);
		setCurrResponse(res);
		return res;
	}

	public void setEndpoint(String endpoint){
		this.endpoint = endpoint;
	}
	
	private String getPath(String path){
		return this.endpoint + path;
	}

	public HashMap<String, String> getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(HashMap<String, String> request_headers) {
		this.requestHeaders = request_headers;
	}

	public HttpResponse getCurrResponse() {
		return currResponse;
	}

	public void setCurrResponse(HttpResponse currResponse) {
		this.currResponse = currResponse;
	}
	
}
