package edu.mit.lastmile.km2.view;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.mit.lastmile.km2.R;

@EViewGroup(R.layout.upload_box)
public class UploadBox extends LinearLayout {

	@ViewById
	protected TextView title, counter, legend;

	
	public UploadBox(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Click
	protected void uploadBtn(){
		
	}

}
