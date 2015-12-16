package com.example.matthew.mobilecoursework;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.location.Location.distanceBetween;

/**
 * Created by matthew on 15/12/2015.
 */
public class mcMapActivity extends FragmentActivity {
    // list of map data object
    List<mcMapData> mapDataList;
    private Marker[] mapDataMarkerList = new Marker[5];
    private GoogleMap mapStarSigns;
    private float markerColours[] = {210.0f,120.0f,300.0f,330.0f,270.0f};
    FragmentManager fmAboutDialgue;// needed for about

    private LatLng latlngGlasgow = new LatLng(55.8580, -4.2590);

    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.mc_map_view);

        fmAboutDialgue = this.getFragmentManager();
        //connecting to db
        mapDataList = new ArrayList<mcMapData>();
        mcMapDataDBMgr mapDB = new mcMapDataDBMgr(this, "mapEKFamous5.s3db",null,1);
        try
        {
            mapDB.dbCreate();
        }catch(IOException e){
            e.printStackTrace();
        }

        mapDataList = mapDB.allMapData();
        SetUpMap();
        AddMarkers();

    }

    public boolean SetUpMap(){
        mapStarSigns =((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        if(mapStarSigns != null){
            mapStarSigns.moveCamera(CameraUpdateFactory.newLatLngZoom(latlngGlasgow, 12));
            mapStarSigns.setMyLocationEnabled(true);
            mapStarSigns.getUiSettings().setCompassEnabled(true);
            mapStarSigns.getUiSettings().setMyLocationButtonEnabled(true);
            mapStarSigns.getUiSettings().setRotateGesturesEnabled(true);
            // adding functionality to location button
            mapStarSigns.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    Log.e("s", "t");
                    //using current loaction
                    Location l = mapStarSigns.getMyLocation();
                    double lat = l.getLatitude();
                    double lng = l.getLongitude();
                    LatLng myLatLng = new LatLng(lat,lng);
                    //updating camera adding line and calculating distance
                    mapStarSigns.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 12));
                    mapStarSigns.addPolyline(new PolylineOptions().geodesic(true)
                            .add(myLatLng)
                            .add(latlngGlasgow)
                    );
                    float[] dist = new float[1];
                    //ToDO: calculate locatoin between these two points
                    distanceBetween(myLatLng.latitude, myLatLng.longitude, latlngGlasgow.latitude, latlngGlasgow.longitude, dist);
                    float s =dist[0] * 0.000621371192f;// ToDo: calculating something dont know if its km or miles or accurate
                    String distance = Float.toString(s);
                    Context context = getApplicationContext();


                    Toast toast = Toast.makeText(context,"The distance between your location and glasgow is: "+ distance + "m" , Toast.LENGTH_LONG);
                    toast.show();
                    return false;
                }
            });


        }

        return false;
    }
// adding markers to map
    public void AddMarkers(){
        MarkerOptions marker;
        mcMapData mapData;
        String mrkTitle;
        String mrkText;

        for(int i =0; i < mapDataList.size(); i++){
            mapData = mapDataList.get(i);
            mrkTitle = mapData.getFirstname() + " " + mapData.getSurname(); //+ "Occupation " + mapData.getOccupation();
            mrkText = "";//"Starsign: "+ mapData.getStarSign();
            marker = SetMarker(mrkTitle, mrkText, new LatLng(mapData.getLatitude(), mapData.getLongitude()), markerColours[i], true);
            mapDataMarkerList[i] = mapStarSigns.addMarker(marker);

        }


    }

    public MarkerOptions SetMarker(String title,String snippet, LatLng position, float markerColour,boolean centreAnchor){

        float anchorX ;
        float anchorY ;

        if(centreAnchor){
            anchorX = 0.5f;
            anchorY = 0.5f;
        }else{
            anchorX = 0.5f;
            anchorY = 1f;
        }
        MarkerOptions marker = new MarkerOptions().title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(markerColour)).anchor(anchorX,anchorY).position(position);

        return marker;
    }
    //Standard Code for menu used in all classes.
    //ToDo: Need to find a way of not repeating this code!
    //ToDo: This is the answer http://stackoverflow.com/questions/15775831/dont-repeat-menu-code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mMain:
                Intent mcMain = new Intent(this, MainActivity.class);
                this.startActivity(mcMain);
                return true;
            case R.id.mRss:
                Intent mcRss = new Intent(this, RssActivity.class);
                this.startActivity(mcRss);
                return true;
            case R.id.mAbout:
                DialogFragment mcAboutDlg = new clsAbout();
                mcAboutDlg.show(fmAboutDialgue, "mcAboutDlg");
                return true;
            case R.id.mSound:
                Intent mcSound = new Intent(this, clsSoundboard.class);
                this.startActivity(mcSound);
                return true;
            case R.id.mMapp:
                Intent mcMapp = new Intent(this,mcMapActivity.class);
                this.startActivity(mcMapp);
                return true;
            case R.id.mSave:
                Intent mcSaved = new Intent(this,mcSavingDataOutput.class);
                this.startActivity(mcSaved);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
