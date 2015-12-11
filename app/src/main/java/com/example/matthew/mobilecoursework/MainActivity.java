package com.example.matthew.mobilecoursework;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //GoogleApiClient mGoogleApiClient;
    //Location mLastLocation;
    FragmentManager fmAboutDialgue;// needed for about
    ListView listView;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //buildGoogleApiClient();
       try{

           String[] test = {"1","2","3","4","5","6","7","8"};

           ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test);

           listView = (ListView) findViewById(R.id.listView);
           listView.setAdapter(adapter);
        }catch (Exception e){


}

        //needed for about
        fmAboutDialgue = this.getFragmentManager();

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


