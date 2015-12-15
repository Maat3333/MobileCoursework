package com.example.matthew.mobilecoursework;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import static android.location.Location.distanceBetween;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FragmentManager fmAboutDialgue;// needed for about


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //needed for about
        fmAboutDialgue = this.getFragmentManager();
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

        LatLng BaltasoundNo2 = new LatLng(60.749, -0.854);
        mMap.addMarker(new MarkerOptions().position(BaltasoundNo2).title("Marker in home"));


        LatLng BarwhillantyHouse = new LatLng(55.014, -3.996);
        mMap.addMarker(new MarkerOptions().position(BarwhillantyHouse).title("Marker in c"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((BarwhillantyHouse), 100f));

  mMap.addPolyline(new PolylineOptions().geodesic(true)
                  .add(BaltasoundNo2)  // Sydney
                  .add(BarwhillantyHouse)

  );

        float[] dist = new float[1];
        //ToDO: calculate locatoin between these two points
        distanceBetween(BaltasoundNo2.latitude, BaltasoundNo2.longitude, BarwhillantyHouse.latitude, BarwhillantyHouse.longitude, dist);
        float s =dist[0] * 0.000621371192f;// ToDo: calculating something dont know if its km or miles or accurate
        String distance = Float.toString(s);
        Log.d("long", distance);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mMap:
                Intent mcMap = new Intent(this, MapsActivity.class);
                this.startActivity(mcMap);
                return true;
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}




