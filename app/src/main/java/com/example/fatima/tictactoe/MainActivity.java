package com.example.fatima.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // to exit the game
    public void exit_click(View v)
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        dlgAlert.setMessage("are you sure you want to exit");
        dlgAlert.setTitle("Exit");

        dlgAlert.setCancelable(true);
        dlgAlert.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                });
        dlgAlert.create().show();
    }
    // moving to about page
    public void about_click(View view){
        Intent myIntent = new Intent(this,AboutActivity.class);
        startActivity(myIntent);
    }
    //method to go to the gameActivity
    public void normal_click(View v)
    {
        Intent myIntent = new Intent(MainActivity.this,GameActivity.class);
        startActivity(myIntent);
    }



}
