package com.nyu.cloud.RemindMeNow;

public class placedata {
	String name_P;
	double lat_p;
	double lng_p;
	
	public void setname_P(String name) {
        this.name_P = name;
    }

    public String getname_P() {
        return name_P;
    }
    
    public void setlat_p(double lat) {
        this.lat_p = lat;
    }

    public double getlat_p() {
        return lat_p;
    }
    
    public void setlng_p(double lng) {
        this.lng_p = lng;
    }

    public double getlng_p() {
        return lng_p;
    }
    
    public String toString(){
    	return String.format("Name: %s Latitude: %f Longitude: %f", name_P, lat_p, lng_p);
    }
}
