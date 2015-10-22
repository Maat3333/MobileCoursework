package com.example.matthew.mobilecoursework;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testButton = (Button) findViewById(R.id.button1);
        testButton.setOnClickListener(this);
        Log.e("n", "got to here");
    }


    @Override
    public void onClick(View v) {
        Intent test = new Intent(getApplicationContext(),MapsActivity.class);

        Log.e("n", "got to here");
        startActivity(test);
    }
}
