package com.example.travelers_calculator.onboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.travelers_calculator.R;

public class OnboardingAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;


    public int[] icons = {R.drawable.ic_calculator, R.drawable.ic_kg_units, R.drawable.ic_access_time, R.drawable.ic_save};
    public String[] headers = {"Welcome to Traveler's Calculator!", "Convert with ease", "Keep track of time", "Save your calculations"};
    public String[] descriptions = {
            "Perform basic calculations",
            "With US and metric units and up-to-date currency rates",
            "No matter where you are in the world",
            "History to keep up with your last saved calculations" +
                    "" +
                    ""
    };

    public OnboardingAdapter(Context mContext){
        this.context = mContext;
    }


    @Override
    public int getCount() {
        return headers.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //set view
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.onboarding_p1, container, false); //this inflates the calculator page?
        TextView headerView = view.findViewById(R.id.headerView);
        TextView subtextView = view.findViewById(R.id.subtextView);
        ImageView imageView = view.findViewById(R.id.calculator_imageview);

        //instantiate array
        imageView.setImageResource(icons[position]);
        headerView.setText(headers[position]);
        subtextView.setText(descriptions[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
