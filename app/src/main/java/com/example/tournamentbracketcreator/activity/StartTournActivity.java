package com.example.tournamentbracketcreator.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.PlayerpoolAFragment;
import com.example.tournamentbracketcreator.fragment.PlayerpoolBFragment;

public class StartTournActivity extends AppCompatActivity {
    public static final String TAG = "StartTournActivity";
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tourn);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //TODO add poolfragments/activity to backstack
        //TODO inflate menu

        ViewPager vpPager = (ViewPager) findViewById(R.id.tourn_pool_vp);
        adapterViewPager = new PagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setClipToPadding(false);
        vpPager.setPageMargin(12);

    }

    public static class PagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return PlayerpoolAFragment.newInstance(0, "Page #1");
                case 1:
                    return PlayerpoolBFragment.newInstance(1, "Page #2");
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }
        @Override
        public float getPageWidth(int position) {
        return 0.85f;
    }
    }

}
