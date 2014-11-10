package com.nyu.cloud.RemindMeNow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.nyu.cloud.RemindMeNow.HttpConnection;
import com.nyu.cloud.RemindMeNow.GooglePlaceAPI;
import com.nyu.cloud.RemindMeNow.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
 
public class TestActivity extends Activity implements android.location.LocationListener {

	private LocationManager mLocationManager = null;
	private static final String TAG = "Location Updates";
	ArrayList<placedata> arraylist_place = new ArrayList<placedata>();
	private static final int LOCATION_INTERVAL = 1000;
	private static final float LOCATION_DISTANCE = 10f;
	private static double latitude=40.679958;
	private static double longitude=-73.939129;
	private static long mtime;
	TextView txtView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_test);
		
		
		
		/*
		txtLat = (TextView) findViewById(R.id.textview1);
		 
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		}
		@Override
		public void onLocationChanged(Location location) {
		txtLat = (TextView) findViewById(R.id.textview1);
		txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
		}
		 
		@Override
		public void onProviderDisabled(String provider) {
		Log.d("Latitude","disable");
		}
		 
		@Override
		public void onProviderEnabled(String provider) {
		Log.d("Latitude","enable");
		}
		 
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d("Latitude","status");
		}*/
		
		
		
		
		
		/*
		
		Log.i(TAG, "onCreate");
		LocationListener[] mLocationListeners = new LocationListener[] 
				{
				        new LocationListener(LocationManager.GPS_PROVIDER),
				        new LocationListener(LocationManager.NETWORK_PROVIDER)
				};
	    initializeLocationManager();
	    try {
	        mLocationManager.requestLocationUpdates(
	                LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
	                mLocationListeners[1]);
	    } catch (java.lang.SecurityException ex) {
	        Log.i(TAG, "fail to request location update, ignore", ex);
	    } catch (IllegalArgumentException ex) {
	        Log.d(TAG, "network provider does not exist, " + ex.getMessage());
	    }
	    try {
	        mLocationManager.requestLocationUpdates(
	                LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
	                mLocationListeners[0]);
	    } catch (java.lang.SecurityException ex) {
	        Log.i(TAG, "fail to request location update, ignore", ex);
	    } catch (IllegalArgumentException ex) {
	        Log.d(TAG, "gps provider does not exist " + ex.getMessage());
	    }
		*/
		LocationManager locationManager;
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,LOCATION_INTERVAL, LOCATION_DISTANCE, this);
		
		txtView=(TextView)findViewById(R.id.txtView);
		System.out.println("Inside Places API");
		
		ArrayList<String> category_arr = new ArrayList<String>();
		GetUniqueCat GetUniqueCat_ob = new GetUniqueCat();
		category_arr = GetUniqueCat_ob.cat_to_search();
		
		for (int s=0; s<category_arr.size(); s++){
			System.out.println("********************************************************************");
			System.out.println (category_arr.get(s));
		//}
		System.out.println("********************************************************************");
		String category = category_arr.get(s);
		Double lat=latitude;
        Double lng=longitude;
		Log.d("Latitude",lat.toString());
		Log.d("Longitude",lng.toString());
		String url = "https://maps.googleapis.com/maps/api/place/search/json" +
				  "?types="+category+
				  "&rankby=distance" +
				  "&location="+latitude+","+longitude +
				  "&sensor=false" +
				  "&key=AIzaSyAhT6KT-FR95hsJL54JEzbPYKOrWCWV6zU";
		ReadTask downloadTask = new ReadTask();
		//String data = readTask(url);
		String data =null;
		try {
			data = downloadTask.execute(url).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String check="Name: " + arraylist_place.get(0).getname_P();
		
		String check = arraylist_place.get(0).getname_P();
		txtView.setText(check);

		
	}
		
	
	}
	@Override
	public void onLocationChanged(Location location) {
	latitude=location.getLatitude();
	longitude=location.getLongitude();
	//txtView.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
	}
	 
	@Override
	public void onProviderDisabled(String provider) {
	Log.d("Latitude","disable");
	}
	 
	@Override
	public void onProviderEnabled(String provider) {
	Log.d("Latitude","enable");
	}
	 
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	Log.d("Latitude","status");
	}
	
	protected static String getJSON(String url) {
		  return getUrlContents(url);
	}

	private static String getUrlContents(String theUrl) {
		  StringBuilder content = new StringBuilder();
		  try {
		   URL url = new URL(theUrl);
		   URLConnection urlConnection = url.openConnection();
		   
		   BufferedReader bufferedReader = new BufferedReader(
		     new InputStreamReader(urlConnection.getInputStream()), 8);
		   String line;
		   
		   while ((line = bufferedReader.readLine()) != null) {
			  //System.out.println(line);
			   
		    content.append(line + "\n");
		   }
		   bufferedReader.close();
		  }catch (Exception e) {
		   e.printStackTrace();
		  }
		 // return content;
		  return content.toString();
		 }
	

	private class ReadTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... url) {
			
			
			
			String urlString=url[0];
			
			
			
			String json = getJSON(urlString);
			
			//System.out.println(json);
			try {
				JSONObject jsonObj = new JSONObject(json.toString());
				JSONArray jsonarray = jsonObj.getJSONArray("results");
				System.out.println("length is " +jsonarray.length());
				
				
				
				for(int i=0; i<jsonarray.length(); i++){
					placedata pdata_obj = new placedata();
					JSONObject jsonObj_loc = jsonarray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location");
					double lng = jsonObj_loc.getDouble("lng");
					double lat = jsonObj_loc.getDouble("lat");
					String name = jsonarray.getJSONObject(i).getString("name");
					pdata_obj.setname_P(name);
					pdata_obj.setlat_p(lat);
					pdata_obj.setlng_p(lng);
					
					arraylist_place.add(pdata_obj);
					
				}
				//System.out.println("********************************************************************");
				//System.out.println(arraylist_place.size());
				
				/*for (int i = 0; i < arraylist_place.size(); i++) {
					//System.out.println(i);
					//System.out.println("Name: " + arraylist_place.get(i).getname_P());
					System.out.println("Name: " + arraylist_place.get(i).getname_P() +
						"     Latitude: " + arraylist_place.get(i).getlat_p() +
						"     Longitude: " + arraylist_place.get(i).getlng_p());   
				}	*/
			} catch (JSONException e) {
	            System.out.println(e);
	        }
			
			
			return arraylist_place.get(0).getname_P();
		}

}
	private void initializeLocationManager() 
	{
	    Log.i(TAG, "initializeLocationManager");
	    if (mLocationManager == null) {
	        mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
	    }
	}
	public String POST(String url){
        InputStream inputStream = null;
        String result = "";
        try {
 
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("command", "UPDATE_LOCATION");
            jsonObject.accumulate("latitude", latitude);
            jsonObject.accumulate("longitude", longitude);
            jsonObject.accumulate("datetime", mtime);
            json = jsonObject.toString();
            Log.i("JsonString", json);
            StringEntity se = new StringEntity(json);
            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null)
            {
                result = convertInputStreamToString(inputStream);
            }
            else
                result = "Did not work!";
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        return result;
    }
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
        {
            result += line;
        }
        Log.i("UPDATE_LOCATION", result);
	    inputStream.close();
	    return result;
 
    }
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) 
        {
            return POST(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "UPDATE_LOCATION executed sucessfully!", Toast.LENGTH_SHORT).show();
       }
    }
}




