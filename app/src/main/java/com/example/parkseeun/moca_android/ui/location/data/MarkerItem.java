package com.example.parkseeun.moca_android.ui.location.data;

public class MarkerItem {
    int position ;
    boolean clickedmarker ;
    double lat;
    double lon;


    public MarkerItem(double lat, double lon, int position,boolean clickedmarker) {
        this.position = position;
        this.lat = lat;
        this.lon = lon;
        this.clickedmarker = clickedmarker;
    }
    public double getLat() {
        return lat;
    }
    public int getPositon() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public boolean getmarker() {
        return clickedmarker;
    }
    public void setmarker(boolean clickedmarker) {
        this.clickedmarker = clickedmarker;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
}
