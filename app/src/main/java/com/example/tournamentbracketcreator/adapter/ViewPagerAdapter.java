package com.example.tournamentbracketcreator.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
        Log.d(TAG, "getItem: returnNewInstance of fragment");
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
        Log.d(TAG, "instantiateItem: starts");
        return super.instantiateItem(container, position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TAG, "getPageTitle: page title");
        String title = getItem(position).getClass().getName();
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG, "destroyItem: starts");
        super.destroyItem(container, position, object);
    }
}
