package com.example.travelers_calculator.units;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.travelers_calculator.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class FragmentSwitcher
{


    String US_TAG = "usSwitch";
    String METRIC_TAG = "metricSwitch";

    public FragmentSwitcher(String _ustag, String metrictag){
        this.US_TAG = _ustag;
        this.METRIC_TAG = metrictag;
    }
    FragmentManager fragmentManager = new FragmentManager() {
        @NonNull
        @Override
        public FragmentTransaction beginTransaction() {
            return null;
        }

        @Override
        public boolean executePendingTransactions() {
            return false;
        }

        @Nullable
        @Override
        public Fragment findFragmentById(int id) {
            return null;
        }

        @Nullable
        @Override
        public Fragment findFragmentByTag(@Nullable String tag) {
            return null;
        }

        @Override
        public void popBackStack() {

        }

        @Override
        public boolean popBackStackImmediate() {
            return false;
        }

        @Override
        public void popBackStack(@Nullable String name, int flags) {

        }

        @Override
        public boolean popBackStackImmediate(@Nullable String name, int flags) {
            return false;
        }

        @Override
        public void popBackStack(int id, int flags) {

        }

        @Override
        public boolean popBackStackImmediate(int id, int flags) {
            return false;
        }

        @Override
        public int getBackStackEntryCount() {
            return 0;
        }

        @NonNull
        @Override
        public BackStackEntry getBackStackEntryAt(int index) {
            return null;
        }

        @Override
        public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

        }

        @Override
        public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

        }

        @Override
        public void putFragment(@NonNull Bundle bundle, @NonNull String key, @NonNull Fragment fragment) {

        }

        @Nullable
        @Override
        public Fragment getFragment(@NonNull Bundle bundle, @NonNull String key) {
            return null;
        }

        @NonNull
        @Override
        public List<Fragment> getFragments() {
            return null;
        }

        @Nullable
        @Override
        public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment f) {
            return null;
        }

        @Override
        public boolean isDestroyed() {
            return false;
        }

        @Override
        public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb, boolean recursive) {

        }

        @Override
        public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb) {

        }

        @Nullable
        @Override
        public Fragment getPrimaryNavigationFragment() {
            return null;
        }

        @Override
        public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {

        }

        @Override
        public boolean isStateSaved() {
            return false;
        }
    };

    public void manageFragmentSwitcher(String tag)
    {
        switch (tag)
        {
            case "US_TAG":
            {
                if (fragmentManager.findFragmentByTag(US_TAG)!= null)
                {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(US_TAG)).commit();
                }
                else
                    {
                        //if the fragment does not exist, add it to fragment manager.
                        fragmentManager.beginTransaction().add(R.id.length_layout,new LengthFragment(), US_TAG).commit();

                    }
                if (fragmentManager.findFragmentByTag(METRIC_TAG) != null)
                {
                //if the other fragment is visible, hide it.
                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(METRIC_TAG)).commit();
                }

            }
            case "METRIC_TAG":
            {
                if (fragmentManager.findFragmentByTag(METRIC_TAG) != null)
                {
                //if the fragment exists, show it.
                fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(METRIC_TAG)).commit();
                }
                else
                    {
                         //if the fragment does not exist, add it to fragment manager.
                        fragmentManager.beginTransaction().add(R.id.length_layout_rev, new LengthFragmentRev(), METRIC_TAG).commit();
                    }
                if (fragmentManager.findFragmentByTag(US_TAG) != null)
                {
                //if the other fragment is visible, hide it.
                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(US_TAG)).commit();

                }
            }

        }

    }
}
