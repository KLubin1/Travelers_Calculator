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
                //.withHeaderText("Header")
                //.withFooterText("Footer")
                //.withBeforeLogoText("Before Logo Text")
                .withAfterLogoText("Traveler's Calculator Demo")
                .withLogo(R.mipmap.ic_logo_foreground);

        getSupportActionBar().hide();
        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        View splashScreen = config.create();
        setContentView(splashScreen);


    }
}
