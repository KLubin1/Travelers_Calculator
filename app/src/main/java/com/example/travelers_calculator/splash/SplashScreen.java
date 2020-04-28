package com.example.travelers_calculator.splash;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelers_calculator.MainActivity;
import com.example.travelers_calculator.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.splash_screen);

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#03DAC5"))
                .withAfterLogoText("Traveler's Calculator")
                .withLogo(R.mipmap.ic_logo_foreground);

        //getSupportActionBar().hide();
        //animating logo
        config.getLogo()
                .animate()
                .rotation(360)
                .setStartDelay(800)
                .setDuration(1500);
        //text color
        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        //render
        View splashScreen = config.create();
        setContentView(splashScreen);
    }
}
