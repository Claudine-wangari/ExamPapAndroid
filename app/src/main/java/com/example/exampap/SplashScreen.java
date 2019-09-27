package com.example.exampap;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.rbddevs.splashy.Splashy;


public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setUpSplashy();
    }

    private void setUpSplashy(){


        new Splashy(this) 		 // For JAVA : new Splashy(this)
                .setLogo(R.drawable.strathimage)
                .setTitleColor("#000000")
                .setProgressColor(R.color.design_default_color_primary_dark)
                .setFullScreen(true)
                .setTime(5000)
                .show();
    }


}
