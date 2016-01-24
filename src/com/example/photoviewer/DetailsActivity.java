package com.example.photoviewer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import com.squareup.picasso.Picasso;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends Activity {
	ArrayList<gallary> data;
	public final String PHOTO_DETAIL = "photoDetail";
	int position; //
	TextView title;
	TextView PubDate;
	TextView owner;
	ImageView image;
	ProgressDialog progressDialog;
	Context context;

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		PubDate = (TextView) findViewById(R.id.textView1);
		title = (TextView) findViewById(R.id.textView2);
		owner = (TextView) findViewById(R.id.textView3);
		image = (ImageView) findViewById(R.id.imageView1);

		data = (ArrayList<gallary>) getIntent().getExtras().getSerializable(
				PHOTO_DETAIL);
		String positionS = getIntent().getExtras().getString("POSITION");
		position = Integer.parseInt(positionS);

		title.setText(data.get(position).getTitle());
		PubDate.setText(data.get(position).getPub_date());
		owner.setText("By: " + data.get(position).getOwner());
		if (isConnectedOnline()) {
			Picasso.with(DetailsActivity.this)
					.load(data.get(position).getImageurl()).into(image);
		} else {
			image.setImageResource(R.drawable.nonetimg);
			Toast.makeText(DetailsActivity.this, "No Network Connection",
					Toast.LENGTH_LONG).show();
		}

		image.setOnTouchListener(new LateralSwipeListener(context) {
			@Override
			public void onSwipeRight() {
				if (position > 0) {
					position--;
				} else {
					position = data.size() - 1;
				}

				PubDate = (TextView) findViewById(R.id.textView1);
				title = (TextView) findViewById(R.id.textView2);
				owner = (TextView) findViewById(R.id.textView3);
				image = (ImageView) findViewById(R.id.imageView1);
				title.setText(data.get(position).getTitle());
				PubDate.setText(data.get(position).getPub_date());
				owner.setText("By: " + data.get(position).getOwner());
				image.setImageResource(R.drawable.ic_launcher);
				if (isConnectedOnline()) {
					Picasso.with(DetailsActivity.this)
							.load(data.get(position).getImageurl()).into(image);
				} else {
					image.setImageResource(R.drawable.nonetimg);
					Toast.makeText(DetailsActivity.this,
							"No Network Connection", Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onSwipeLeft() {
				if (position < data.size() - 1) {
					position++;
				} else {
					position = 0;
				}

				PubDate = (TextView) findViewById(R.id.textView1);
				title = (TextView) findViewById(R.id.textView2);
				owner = (TextView) findViewById(R.id.textView3);
				image = (ImageView) findViewById(R.id.imageView1);
				title.setText(data.get(position).getTitle());
				PubDate.setText(data.get(position).getPub_date());
				owner.setText("By: " + data.get(position).getOwner());
				image.setImageResource(R.drawable.ic_launcher);
				if (isConnectedOnline()) {
					Picasso.with(DetailsActivity.this)
							.load(data.get(position).getImageurl()).into(image);
				} else {
					image.setImageResource(R.drawable.nonetimg);
					Toast.makeText(DetailsActivity.this,
							"No Network Connection", Toast.LENGTH_LONG).show();
				}
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
