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
                DialogFragment mcAboutDlg = new mcAboutDialogue();
                mcAboutDlg.show(fmAboutDialgue, "mcAboutDlg");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}




