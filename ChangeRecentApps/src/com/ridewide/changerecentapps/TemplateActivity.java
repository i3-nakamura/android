package com.ridewide.changerecentapps;

//import android.content.Intent;
import android.app.ListActivity;
import android.os.Bundle;
//import android.widget.Toast;

public class TemplateActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		int launchFlags = getIntent().getFlags();
//		if ((launchFlags&Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) != 0) {
//			Toast.makeText(mContext, "Please do not look", Toast.LENGTH_SHORT).show();
//		}
		finish();
	}
//	@Override
//	protected void onResume() {
//		super.onResume();
//		finish();
//	}
}
