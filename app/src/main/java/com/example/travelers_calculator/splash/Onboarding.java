package com.example.travelers_calculator.splash;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.travelers_calculator.R;

public class Onboarding extends AppCompatActivity
{

    private ViewPager viewpager;
    private LinearLayout dotLayout;
    private OnboardingAdapter onboardingAdapter;
    private TextView[] dots;
    private Button goButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_layout);

        viewpager = findViewById(R.id.onboardingViewPager);
        dotLayout = findViewById(R.id.dots_layout);
        goButton = findViewById(R.id.buttonNext);
        //prevButton = findViewById(R.id.buttonPrev);


        onboardingAdapter = new OnboardingAdapter(this);
        viewpager.setAdapter(onboardingAdapter);

        viewpager.addOnPageChangeListener(viewPagerListener);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });

    }


    public void addDotsIndicator(int position)
    {
        dots = new TextView[4];
        dotLayout.removeAllViews();
        for(int d = 0; d<dots.length; d++)
        {
            dots[d] =new TextView(this);
            dots[d].setText(Html.fromHtml("&#8226"));
            dots[d].setTextSize(40);
            dots[d].setTextColor(getResources().getColor(R.color.background_white));

            dotLayout.addView(dots[d]);

        }

        if(dots.length > 0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.divider));

        }
    }

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }


        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);

            //changed it so that there's only one button, "Get Calculating!", and set an on click listener event for that
            //when the last page is reached, this should bypass the third page problem.
            if(position==dots.length-1)
            {
                goButton.setEnabled(true);
                goButton.setVisibility(View.VISIBLE);
                goButton.setText("Get Calculating!");

            }
            //to disable button unless its on the last page, not really a big deal, though, and maybe shouldnt be?
            else{
                goButton.setEnabled(false);
                goButton.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
/*
---old code from onboarding library

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //super.onCreateView(savedInstanceState);
        //setContentView(R.layout.onboarding_layout);


        View view = inflater.inflate(R.layout.onboarding_layout, container, false);

        //to set onboarding
        PaperOnboardingFragment onboardingFragment = PaperOnboardingFragment.newInstance(getPages());
        // getSupportActionBar().hide();
        getFragmentManager().beginTransaction().add(R.id.onboarding_layout, onboardingFragment).commit();

        onboardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                //start on calculator by default
                //getSupportActionBar().show();
                //Intent proceed = new Intent(Onboarding.this, MainActivity.class);
                //startActivity(proceed);
                //setContentView(R.layout.activity_main);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatorFragment()).commit();

            }
        });

        return  view;
    }

    public ArrayList<PaperOnboardingPage> getPages()
    {
        PaperOnboardingPage p1 = new PaperOnboardingPage(
                "Welcome to Traveler's Calculator!",
                "Do basic calculations.",
                getResources().getColor(R.color.colorAccent),
                0,
                R.drawable.ic_calculator);
        PaperOnboardingPage p2 = new PaperOnboardingPage(
                "Convert with ease",
                "With US and metric units and up-to-date currency rates.",
                getResources().getColor(R.color.colorAccent),
                0,
                R.drawable.ic_kg_units);
        PaperOnboardingPage p3 = new PaperOnboardingPage(
                "Keep track of time",
                "No matter where you are in the world.",
                getResources().getColor(R.color.colorAccent),
                0,
                R.drawable.ic_access_time);

        PaperOnboardingPage p4 = new PaperOnboardingPage(
                "Save your calculations",
                "History to keep up with your last saved calculations.",
                getResources().getColor(R.color.colorAccent),
                0,
                R.drawable.ic_save);

        ArrayList<PaperOnboardingPage> onboardingPages = new ArrayList<>();
        onboardingPages.add(p1);
        onboardingPages.add(p2);
        onboardingPages.add(p3);
        onboardingPages.add(p4);

        return onboardingPages;

    }*/
