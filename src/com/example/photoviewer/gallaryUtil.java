package com.example.photoviewer;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class gallaryUtil {
	static public class gallaryJSONParser {
		static ArrayList<gallary> parseGallery(String in) throws JSONException {
			ArrayList<gallary> gallary = new ArrayList<gallary>();
			JSONObject object = new JSONObject(in);
			JSONArray PhotoJSONArray = object.getJSONArray("photos");
			for (int i = 0; i < PhotoJSONArray.length(); i++) {
				gallary gal = new gallary();
				JSONObject photoDetails = PhotoJSONArray.getJSONObject(i);
				gal.setTitle(photoDetails.getString("photo_title"));
				gal.setPub_date(photoDetails.getString("upload_date"));
				gal.setOwner(photoDetails.getString("owner_name"));
				gal.setImageurl(photoDetails.getString("photo_file_url"));
				gallary.add(gal);
			}
			return gallary;
		}
	}
}
