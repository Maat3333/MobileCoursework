package com.example.matthew.mobilecoursework;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by matthew on 15/12/2015.
 */
//Class for making sounds play on a button press and stop when the stop button is pressed
public class clsSoundboard extends AppCompatActivity {
    // needed for the about dialog
    FragmentManager fmAboutDialgue;
    // used to play sounds
    MediaPlayer mp;
    // stores 'this' so it can be used in a subclass
    private clsSoundboard local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        local = this;
        //needed for about
        fmAboutDialgue = this.getFragmentManager();

        //Sets up button and assigns on click listener to play a sound clip
        Button btnRain = (Button)this.findViewById(R.id.btnRain);
        btnRain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             //   mp.stop();
                mp = MediaPlayer.create(local, R.raw.rain);
                mp.start();
            }
        });

        //Sets up button and assigns on click listener to play a sound clip
        Button btnWind = (Button)this.findViewById(R.id.btnWind);
        btnWind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp = MediaPlayer.create(local, R.raw.wind);
            //    mp.stop();
                mp.start();
            }
        });

        //Sets up button and assigns on click listener to play a sound clip
        Button btnThunder = (Button)this.findViewById(R.id.btnThunder);
        btnThunder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            //    mp.stop();
                mp = MediaPlayer.create(local, R.raw.thunder);
                mp.start();
            }
        });
        //Sets up button and assigns on click listener to stop all sounds
        Button btnStop = (Button)this.findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.stop();
            }
    });

    }

    //Standard Code for menu used in all classes.
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
