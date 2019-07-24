package com.example.tournamentbracketcreator.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.tournamentbracketcreator.fragment.PlayerpoolAFragment;
import com.example.tournamentbracketcreator.fragment.PlayerpoolBFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public static final String TAG = "ViewPagerAdapter";
    public static int NUM_ITEMS = 2;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        //return new PlayerpoolAFragment();
        if (position == 0){
            Log.d(TAG, "getItem: position 0");
            return new PlayerpoolAFragment().newInstance(0, "Page #1");
//            return new PlayerpoolAFragment();
        } else {
            Log.d(TAG, "getItem: position 1");
            return new PlayerpoolBFragment().newInstance(0, "Page #2");
        }


        /*switch (position){
            *//*case 0: Original 7/16/2019 17:10
                Log.d(TAG, "getItem: 0");
                return new PlayerpoolAFragment().newInstance(0, "Page #1");
            case 1:
                Log.d(TAG, "getItem: 1");
                return new PlayerpoolBFragment().newInstance(1, "Page #2");*//*
            *//*case 0:
                Log.d(TAG, "getItem: 0");
                return new PlayerpoolAFragment();

            case 1:
                Log.d(TAG, "getItem: 1");
                return new PlayerpoolBFragment();*//*


        }
        return null;*/

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    /*@Override
    public float getPageWidth(int position) {
        return 0.93f;
    }*/

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = getItem(position).getClass().getName();
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
