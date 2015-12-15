package com.example.matthew.mobilecoursework;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by matthew on 15/12/2015.
 */
public class SoundBoard extends Activity {

    FragmentManager fmAboutDialgue;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        //needed for about
        fmAboutDialgue = this.getFragmentManager();

        Button btnRain = (Button)this.findViewById(R.id.btnRain);
        mp = MediaPlayer.create(this, R.raw.rain);
        btnRain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.start();
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
            case R.id.mSound:
                Intent mcSound = new Intent(this, SoundBoard.class);
                this.startActivity(mcSound);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
