package com.example.bartek.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Bartek on 2016-10-27.
 */

public class MainMenu extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_menu);
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog exitAlert = createExitAlert();
        exitAlert.show();
    }

    private AlertDialog createExitAlert()
    {
        AlertDialog.Builder exitAlertDialogBuilder = new AlertDialog.Builder(this);

        exitAlertDialogBuilder.setTitle("Exit");
        exitAlertDialogBuilder.setMessage("Are you sure want to Exit?");
        exitAlertDialogBuilder.setPositiveButton("Yes",
            new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
            }
        });
        exitAlertDialogBuilder.setNegativeButton("No",
            new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
            }
        });

        AlertDialog exitAlertDialog = exitAlertDialogBuilder.create();

        return exitAlertDialog;
    }
}
