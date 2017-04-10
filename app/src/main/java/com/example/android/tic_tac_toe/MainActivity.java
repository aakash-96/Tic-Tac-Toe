package com.example.android.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void one_Player(View v)
    {
        Intent myIntent = new Intent(MainActivity.this,One_Player.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void two_Player(View v)
    {
        Intent myIntent = new Intent(MainActivity.this,Two_Player.class);
        MainActivity.this.startActivity(myIntent);
    }
}
