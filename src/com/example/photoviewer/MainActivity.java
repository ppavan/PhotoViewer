package com.example.photoviewer;

import java.util.ArrayList;

import com.example.hw2.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ArrayList<gallary> data;
	ListView myListView;
	ArrayAdapter<String> adapter;
	gallary gal;
	ArrayList<String> pTitles;
	public final String PHOTO_DETAIL = "photoDetail";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (isConnectedOnline()) {
			new GetgallaryAsyncTask(MainActivity.this)
					.execute("http://www.panoramio.com/map/get_panoramas.php?set=public&from=0&to=50&size=medium");
		} else {
			Toast.makeText(MainActivity.this, "No Network Connection",
					Toast.LENGTH_LONG).show();
			Toast.makeText(MainActivity.this,
					"Please relaunch when connected to Network ",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	public void getdata(ArrayList<gallary> photoList) {
		data = new ArrayList<gallary>();
		pTitles = new ArrayList<String>();
		data = photoList;

		for (int i = 0; i < data.size(); i++) {
			gal = data.get(i);
			pTitles.add(gal.getTitle());
		}
		myListView = (ListView) findViewById(R.id.listView1);

		// Please change to Java 1.7
		adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
				pTitles);
		myListView.setAdapter(adapter);

		myListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this,
						DetailsActivity.class);
				intent.putExtra(PHOTO_DETAIL, data);
				intent.putExtra("POSITION", position + "");
				startActivity(intent);
			}
		});
	}

	private boolean isConnectedOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}
}
