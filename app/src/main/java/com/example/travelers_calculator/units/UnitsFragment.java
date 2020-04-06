package com.example.travelers_calculator.units;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.travelers_calculator.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class UnitsFragment extends Fragment
{
   private SectionsPagerAdapter mSectionsPagerAdapter;
   private ViewPager2 mViewPager2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_units, container, false);
        //where it comes together, what usually comes in the units main activity goes here

        //mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), getLifecycle());
        //setupViewPager(mViewPager2);


        ViewPager2 viewPager = v.findViewById(R.id.view_pager2);
        viewPager.setAdapter(new SectionsPagerAdapter(getChildFragmentManager(), getLifecycle()));

        TabLayout tabLayout = v.findViewById(R.id.tabs);
        //tabLayout.setupWithViewPager(mViewPager2);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });
        tabLayoutMediator.attach();


        return v;
    }

    private void  setupViewPager(ViewPager2 _viewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getChildFragmentManager(), getLifecycle());// or lifecycleowner
        adapter.addFragment(new LengthFragment(), "Length");
        adapter.addFragment(new AreaFragment(), "Area");
        adapter.addFragment(new WeightFragment(), "Weight");
        adapter.addFragment(new TemperatureFragment(), "Temperature");
        adapter.addFragment(new VolumeFragment(), "Volume");
        _viewPager.setAdapter(adapter);
    }
}
