package com.example.bartek.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.view.View;


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

        Engine.musicThread = new Thread() {
            public void run()
            {
                Intent intent = new Intent(getApplicationContext(), Music.class);
                startService(intent);
                Engine.context = getApplicationContext();
            }
        };

        Engine.musicThread.start();

        Engine engine = new Engine();

        ImageButton startButton = (ImageButton) findViewById(R.id.startButton);
        ImageButton settingsButon = (ImageButton) findViewById(R.id.settingsButton);
        ImageButton exitButton = (ImageButton) findViewById(R.id.exitButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

            }});

        settingsButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(getApplicationContext(), Settings.class);
                MainMenu.this.startActivity(settings);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
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
