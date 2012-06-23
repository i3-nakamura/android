package com.ridewide.changerecentapps;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnItemClickListener {
	Context mContext;
	ArrayList<DummyApp> mList;
	ListAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        
        mList = loadNote();
        
        adapter = new ListAdapter(this, R.layout.row, mList);
        
        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        
        final Button btn1 = (Button)findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int num = mList.size();
				for (int i=0; i<num; i++) {
					DummyApp app = mList.get(i);
					if (app.getCheck()) {
						app.setCheck(false);
						mList.set(i, app);
					}
				}
				adapter.notifyDataSetChanged();
			}
		});
        
        final Button btn3 = (Button)findViewById(R.id.button3);
		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				int num, i;
				ArrayList<DummyApp> runList = new ArrayList<DummyApp>();
				DummyApp app;
				
				//実行リストに入れる
				num = mList.size();
				for (i=0; i<num; i++) {
					app = mList.get(i);
					if (app.getCheck()) {
						runList.add(app);
					}
				}
				//実行
				num = runList.size();
				if (num > 0) {
					for (i=0; i<num; i++) {
						Intent intent = new Intent();
						intent.setClassName(mContext.getApplicationContext(), 
								mContext.getPackageName() + runList.get(i).getAct());
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
						startActivity(intent);
					}
					Toast.makeText(mContext, "Completion of a dummy apps (" + num + ")", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(mContext, "Please select a dummy app", Toast.LENGTH_LONG).show();
				}
			}
		});
    }
    @Override
    protected void onPause() {
    	super.onPause();
    	
    	String str = "";
    	SharedPreferences prefs = getPreferences(MODE_PRIVATE);
    	
		int num = mList.size();
		int cnt=0;
		for (int i=0; i<num; i++) {
			DummyApp app = mList.get(i);
			if (app.getCheck()) {
				if (cnt == 0) {
					str += app.getLabel();
				} else {
					str += "," + app.getLabel();
				}
				cnt++;
			}
		}
		prefs.edit().putString("check", str).commit();
    }
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_info:
			showDialog(0);
		default:
			break;
		}
		return true;
	}
	
	@Override
    protected Dialog onCreateDialog(int id) {
    	PackageManager pm = this.getPackageManager();
    	String versionName = "";
    	String messages;
    	try {
    	   PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
    	   versionName = info.versionName;
    	} catch (NameNotFoundException e) {
    	}
    	messages = getResources().getString(R.string.app_name) + "\n" 
    		+ "Version " + versionName + "\n\n" 
    		+ getResources().getString(R.string.about_message);
    	
		View view = getLayoutInflater().inflate(R.layout.about, null);
		TextView tv;
		tv= (TextView)view.findViewById(R.id.textview_about);
		tv.setText(messages);
		
		tv= (TextView)view.findViewById(R.id.textview_about_link);
		CharSequence charSequence = Html.fromHtml("<a href=\"http://play.google.com/store/apps/details?id=com.ridewide.changerecentapps\">Google Play</a>");
		MovementMethod mMethod = LinkMovementMethod.getInstance();
		tv.setMovementMethod(mMethod);
		tv.setText(charSequence);
		
        return new AlertDialog.Builder(MainActivity.this)
            .setIcon(android.R.drawable.ic_dialog_info)
            .setTitle("About")
            .setView(view)
            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            })
            .create();
    }
    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		DummyApp item = mList.get(position);
		item.setCheck(item.getCheck()?false:true);
		mList.set(position, item);
		
		adapter.notifyDataSetChanged();
	}
	private ArrayList<DummyApp> loadNote() {
		ArrayList<DummyApp> list = new ArrayList<DummyApp>();
		
		int num = DummyApp.appIcon.length;
		int i, j;
		for (i=0; i<num; i++) {
			DummyApp app = new DummyApp(
					DummyApp.appIcon[i], 
					DummyApp.labelName[i],
					DummyApp.activityName[i],
					false
					);
			list.add(app);
		}
		
		//TODO 別メソッドにする
		//チェックの復元
    	SharedPreferences prefs = getPreferences(MODE_PRIVATE);
    	String str = prefs.getString("check", "");
    	
    	String[] arr = str.split(",");
    	for (i = 0; i < arr.length; i++) {
    		for (j = 0; j < num; j++) {
    			if (list.get(j).getLabel().equals(arr[i])) {
    				list.get(j).setCheck(true);
    				break;
    			}
    		}
    	}
		
		return list;
	}
	private class ListAdapter extends ArrayAdapter {
		private LayoutInflater inflater = null;
		private ArrayList<DummyApp> items;
		
		public ListAdapter(Context context, int textViewResourceId,
				ArrayList<DummyApp> list) {
			super(context, textViewResourceId, list);
			inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			items = list;
		}
		@Override
	    public int getCount() {
	      return items.size();
	    }
	    @Override
	    public Object getItem(int position) {
	      return items.get(position);
	    }
	    @Override
	    public long getItemId(int position) {
	      return position;
	    }
	    @Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
		    	convertView = inflater.inflate(R.layout.row, null);
			}
			DummyApp item = (DummyApp)items.get(position);
			if (item != null) {
				ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView1);
				imageView.setImageResource(item.getIcon());
				TextView textView = (TextView)convertView.findViewById(R.id.TextView);
			    textView.setText(item.getLabel());
			    ImageView imageView2 = (ImageView)convertView.findViewById(R.id.imageView2);
				imageView2.setImageResource(
						(item.getCheck() ? R.drawable.toggle_on : R.drawable.toggle_off));
		    }
		    return convertView;
		}
	}
}