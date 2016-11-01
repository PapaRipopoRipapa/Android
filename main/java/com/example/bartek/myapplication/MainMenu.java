package com.example.bartek.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageButton;
import android.view.View;


/**
 * Created by Bartek on 2016-10-27.
 */

public class MainMenu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_menu);

        Intent intent = new Intent(getApplicationContext(), MusicManager.class);
        startService(intent);

        ImageButton startButton = (ImageButton) findViewById(R.id.startButton);
        ImageButton settingsButon = (ImageButton) findViewById(R.id.settingsButton);
        ImageButton exitButton = (ImageButton) findViewById(R.id.exitButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent startGame = new Intent(getApplicationContext(), MainActivity.class);
                MainMenu.this.startActivity(startGame);
            }
        });

        settingsButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(getApplicationContext(), Settings.class);
                MainMenu.this.startActivity(settings);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                exit();
            }
        });
    }

    public void exit()
    {
        if(isExitAlertEnabled()) {
            AlertDialog exitAlert = createExitAlert();
            exitAlert.show();
        }
        else {
            killApplicationProcess();
        }
    }

    private boolean isExitAlertEnabled() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("exit_alert_settings", true);
    }

    private void killApplicationProcess() {
        int myPid = android.os.Process.myPid();
        android.os.Process.killProcess(myPid);
    }

    @Override
    public void onBackPressed()
    {
        exit();
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
                    killApplicationProcess();
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

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
