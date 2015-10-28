package com.example.matthew.mobilecoursework;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import static android.location.Location.distanceBetween;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng home = new LatLng(54.550442, -1.915154);
        mMap.addMarker(new MarkerOptions().position(home).title("Marker in home"));


        LatLng chome = new LatLng(54.543656, -1.922299);
        mMap.addMarker(new MarkerOptions().position(chome).title("Marker in c"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((chome), 100f));

  mMap.addPolyline(new PolylineOptions().geodesic(true)
                  .add(home)  // Sydney
                  .add(chome)

  );
        String distance = "";
        float [] dist = new float[1];
        //ToDO: calculate locatoin between these two points
//distance = distanceBetween(home.latitude,home.longitude,chome.latitude,chome.longitude,dist);
        Log.d("long", distance);
    }
}




