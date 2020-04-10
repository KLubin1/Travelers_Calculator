package com.example.travelers_calculator.units;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerOverride extends ViewPager
{
    PagerAdapter mPagerAdapter;
    public ViewPagerOverride(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (mPagerAdapter != null) {
            super.setAdapter(mPagerAdapter);
        }
    }
    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mPagerAdapter!=null){
            super.setAdapter(mPagerAdapter);
        }

    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
    }

    public void storeAdapter(PagerAdapter pagerAdapter) {
        mPagerAdapter = pagerAdapter;
    }
}
