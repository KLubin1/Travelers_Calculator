package com.example.travelers_calculator.units;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.travelers_calculator.R;
import com.google.android.material.tabs.TabLayout;

public class UnitsFragment extends Fragment
{
   private SectionsPagerAdapter mSectionsPagerAdapter;
   private TabLayout tabLayout;
   private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_units, container, false);
        //where it comes together, what usually comes in the units main activity goes here

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());//suspect causing crash

        viewPager = view.findViewById(R.id.view_page_layout);
        viewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        //SharedPreferences prefs = getActivity().getSharedPreferences("UnitResult", Context.MODE_PRIVATE);
        //prefs.getAll();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //SharedPreferences prefs = getActivity().getSharedPreferences("UnitResult", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = prefs.edit();



    }
}
