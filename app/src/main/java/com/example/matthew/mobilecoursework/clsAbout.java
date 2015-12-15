package com.example.matthew.mobilecoursework;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by matthew on 30/09/2015.
 */


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class clsAbout extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder mcAboutDialog = new AlertDialog.Builder(getActivity());
        // ToDo: look into a string.format type thing
        mcAboutDialog.setMessage("This app will show information on Uk weather warnings."
                +System.getProperty("line.separator") +" Created by Matthew Metcalfe 2015")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ToDo: Do i need something in here?
                    }
                });

        // Sets up the popup, name and icon
        mcAboutDialog.setTitle("About");
        mcAboutDialog.setIcon(R.drawable.ic_action_about);

        //Creates the popup
        return mcAboutDialog.create();
    }
}
