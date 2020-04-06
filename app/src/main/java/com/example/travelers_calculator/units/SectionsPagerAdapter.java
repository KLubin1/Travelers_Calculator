package com.example.travelers_calculator.units;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

//seems like various ways to go about this, this is one way
public class SectionsPagerAdapter extends FragmentStateAdapter
{
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentNames = new ArrayList<>();

    public SectionsPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    public void addFragment(Fragment fragment, String title)
    {
        fragmentList.add(fragment);
        fragmentNames.add(title);

    }

    public CharSequence getPageTitle(int position)
    {
        return fragmentNames.get(position);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount()
    {
        return fragmentList.size();
    }

}
