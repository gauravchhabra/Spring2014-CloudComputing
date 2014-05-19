package com.nyu.cloud.RemindMeNow;

import java.net.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlaceAPI {
		
	ArrayList<placedata> arraylist_place = new ArrayList<placedata>();
		public ArrayList<placedata> getPlacesAPI(){
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
			
			String urlString = "https://maps.googleapis.com/maps/api/place/search/json" +
				  "?types="+category+
				  "&rankby=distance" +
				  "&location=40.679958,-73.939129" +
				  "&sensor=false" +
				  "&key=AIzaSyAhT6KT-FR95hsJL54JEzbPYKOrWCWV6zU";
			
			
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
			
			}
			return arraylist_place;
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
		
	

}
