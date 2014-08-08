package edu.mit.lastmile.km2.util;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class ErrorDialog extends DialogFragment {

	private Dialog mDialog;
	
	public ErrorDialog(){
		super();
		mDialog = null;
	}
	
	public void setDialog(Dialog dialog){
		mDialog = dialog;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState){
		return mDialog;
	}

}