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
public class clsSoundboard extends AppCompatActivity {
//ToDo: come back to here and allow for sounds to be stopped and a different one played
    FragmentManager fmAboutDialgue;
    MediaPlayer mp;
    private clsSoundboard local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        local = this;
        //needed for about
        fmAboutDialgue = this.getFragmentManager();

        Button btnRain = (Button)this.findViewById(R.id.btnRain);

        btnRain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             //   mp.stop();
                mp = MediaPlayer.create(local, R.raw.rain);
                mp.start();
            }
        });

        Button btnWind = (Button)this.findViewById(R.id.btnWind);

        btnWind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp = MediaPlayer.create(local, R.raw.wind);
            //    mp.stop();
                mp.start();
            }
        });

        Button btnThunder = (Button)this.findViewById(R.id.btnThunder);

        btnThunder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            //    mp.stop();
                mp = MediaPlayer.create(local, R.raw.thunder);
                mp.start();
            }
        });

        Button btnStop = (Button)this.findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.stop();
            }
    });

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
