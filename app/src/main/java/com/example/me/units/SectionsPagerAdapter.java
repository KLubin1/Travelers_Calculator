package com.example.me.units;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

//seems like various ways to go about this, this is one way
public class SectionsPagerAdapter extends FragmentStatePagerAdapter
{
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentNames = new ArrayList<>();

    private int numOfTabs;

    public SectionsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    /*public SectionsPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }*/

    @Override
    public int getCount()
    {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Length";
            case 1:
                return "Area";
            case 2:
                return "Weight";
            case 3:
                return "Temp";
            case 4:
                return "Volume";
            default:
                return null;
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
       switch (position)
       {
           case 0:
               return new LengthFragment();
           case 1:
               return new AreaFragment();
           case 2:
               return new WeightFragment();
           case 3:
               return new TemperatureFragment();
           case 4:
               return new VolumeFragment();
           default:
               return null;
       }
    }

    /*@Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }*/
/*  @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }*/

   /* @Override
    public int getItemCount()
    {
        return fragmentList.size();
    }*/
   /*public void addFragment(Fragment fragment, String title)
   {
       fragmentList.add(fragment);
       fragmentNames.add(title);

   }*/



}
