package com.example.matthew.mobilecoursework;

import android.content.Intent;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    Button testButton;
    Button testButton2;
    TextView mLatitudeText;
    TextView mLongitudeText;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildGoogleApiClient();

        testButton = (Button) findViewById(R.id.button1);
        testButton.setOnClickListener(this);

        testButton2 = (Button) findViewById(R.id.button2);
        testButton2.setOnClickListener(this);

        mLatitudeText = (TextView) findViewById(R.id.mLatitudeText);
        mLongitudeText =(TextView) findViewById(R.id.mLongditudeText);



    }

    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            Log.d("lat", (String.valueOf(mLastLocation.getLatitude())));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
            Log.d("long", (String.valueOf(mLastLocation.getLongitude())));
        } else {
            Toast.makeText(getApplicationContext(), "Location Null", Toast.LENGTH_SHORT).show();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent test = new Intent(getApplicationContext(), MapsActivity.class);

                startActivity(test);
                break;
            case R.id.button2:

                // need to put the code here to run clsGps and get the latlng
                break;
            }
        }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}

