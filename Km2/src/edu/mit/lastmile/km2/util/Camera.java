package edu.mit.lastmile.km2.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import edu.mit.lastmile.km2.Config;

@EBean(scope = Scope.Singleton)
public class Camera {

	private Activity mActivity;
	private Uri mUri;
	
	public void launchForImage(Fragment f, int code) throws NullPointerException{
		mActivity = f.getActivity();
		f.startActivityForResult(getIntent(), code);
	}
	
	public void launchForImage(Activity activity, int code) throws NullPointerException{
		mActivity = activity;
		activity.startActivityForResult(getIntent(), code);
	}
	
	public Uri getUri(){
		return mUri; //Latest picture
	}
	
	private Intent getIntent() throws NullPointerException{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		mUri = null;
		mUri = getImageUri();
		intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
		return intent;
	}
	
	private Uri getImageUri() throws NullPointerException{
		return Uri.fromFile(getImageMediaFile());
	}
	
	private File getImageMediaFile(){
		File mediaFile = null;
		File mediaDir = new File(mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), Config.Camera.DIR);
		if(!mediaDir.exists()){
			if(!mediaDir.mkdirs()){
				Log.e(Config.LOG_TAG, "Cannot open file for camera");
				return null;
			}
		}
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
		mediaFile = new File(mediaDir.getPath() + File.separator + "img_" + timestamp + ".jpg");
		return mediaFile;
	}
	
}
