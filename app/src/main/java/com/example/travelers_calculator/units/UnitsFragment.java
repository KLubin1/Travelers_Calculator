package com.example.travelers_calculator.units;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.travelers_calculator.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class UnitsFragment extends Fragment
{
   private SectionsPagerAdapter mSectionsPagerAdapter;
   private TabLayout tabLayout;
   private ViewPagerOverride viewPager;
   private TabItem lengthTab, areaTab, weightTab, tempTab, volumeTab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_units, container, false);
        //where it comes together, what usually comes in the units main activity goes here


        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), 5);//suspect causing crash
        tabLayout = v.findViewById(R.id.tab_layout);
        viewPager = v.findViewWithTag(R.id.view_page_layout);
        lengthTab = v.findViewById(R.id.length_tab);
        areaTab = v.findViewById(R.id.area_tab);
        weightTab = v.findViewById(R.id.weight_tab);
        tempTab = v.findViewById(R.id.temp_tab);
        volumeTab = v.findViewById(R.id.volume_tab);

        viewPager.setAdapter(mSectionsPagerAdapter);//where its crashing
        tabLayout.setupWithViewPager(viewPager);
        //setupViewPager(viewPager);

        //tabLayout mediator needed for ViewPager2
       /*TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });
        tabLayoutMediator.attach();*/

        //------------From here on is deprecated territory----------------//
       tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
       {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0)
                    mSectionsPagerAdapter.notifyDataSetChanged();
                if(tab.getPosition() == 1)
                    mSectionsPagerAdapter.notifyDataSetChanged();
                if(tab.getPosition() == 2)
                    mSectionsPagerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//       viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return v;
    }

    //I don't trust the efficacy of this method anymore
    private void  setupViewPager(ViewPagerOverride _viewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getFragmentManager(), tabLayout.getTabCount());// or lifecycleowner
        adapter.addFragment(new LengthFragment(), "Length");
        adapter.addFragment(new AreaFragment(), "Area");
        adapter.addFragment(new WeightFragment(), "Weight");
        adapter.addFragment(new TemperatureFragment(), "Temperature");
        adapter.addFragment(new VolumeFragment(), "Volume");
        _viewPager.setAdapter(adapter);
    }

}
