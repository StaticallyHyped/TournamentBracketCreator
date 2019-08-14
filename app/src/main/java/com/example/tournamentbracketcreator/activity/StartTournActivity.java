package com.example.tournamentbracketcreator.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.AddNewPlayerDialogFragment;
import com.example.tournamentbracketcreator.fragment.PlayerpoolAFragment;
import com.example.tournamentbracketcreator.fragment.PlayerpoolBFragment;
import com.example.tournamentbracketcreator.model.ClientFactory;

public class StartTournActivity extends AppCompatActivity implements PlayerpoolAFragment.ClickOnFragA {
    public static final String TAG = "StartTournActivity";
    FragmentPagerAdapter adapterViewPager;
    private String mSubmitCache;
    private OnButtonClicked mOnButtonClicked;

    //from SO 2
    public interface OnButtonClicked{
        void submit(final String s);
    }
    public void setOnButtonClicked(final OnButtonClicked c){
        mOnButtonClicked = c;
        if (TextUtils.isEmpty(mSubmitCache) == false){
            Log.d(TAG, "setOnButtonClicked: not empty");
            c.submit(mSubmitCache);
        }
        Log.d(TAG, "setOnButtonClicked: empty");
    }
    public void buttonClicked(final String s){
        if (mOnButtonClicked == null){
            Log.d(TAG, "buttonClicked: is null");
            mSubmitCache = s;
            return;
        }
        mOnButtonClicked.submit(s);
        Log.d(TAG, "buttonClicked: not null, submit " + s);
    }
    //end SO 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tourn);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ClientFactory.getInstance(this);

        //TODO add poolfragments/activity to backstack
        //TODO inflate menu

        ViewPager vpPager = (ViewPager) findViewById(R.id.tourn_pool_vp);
        adapterViewPager = new PagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setClipToPadding(false);
        //vpPager.setPageMargin(12);
        vpPager.setPageMargin(-12);

        vpPager.setOffscreenPageLimit(3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_start_tourn, menu);
        return true;
    }

    //from SO 1
    @Override
    public void onClickOnA(int position) {
        Log.d(TAG, "onClickOnA: clicked");
        PlayerpoolBFragment poolBFrag = (PlayerpoolBFragment) getSupportFragmentManager()
                .findFragmentByTag("Page 1");
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
        public float getPageWidth(int position) { return 0.85f;
    }
    }

//Overridden methods for the sake of debugging
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onPostCreate: starts");
        super.onPostCreate(savedInstanceState);
    }


    @Override
    protected void onPostResume() {
        Log.d(TAG, "onPostResume: starts");
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: starts");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: starts");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: starts");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: starts");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: starts");
        super.onPause();
    }

    @Override
    public void onStateNotSaved() {
        Log.d(TAG, "onStateNotSaved: starts");
        super.onStateNotSaved();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: starts");
        super.onResume();
    }

    @Override
    protected void onResumeFragments() {
        Log.d(TAG, "onResumeFragments: starts");
        super.onResumeFragments();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        Log.d(TAG, "onAttachFragment: starts");
        if (fragment instanceof PlayerpoolAFragment){
            PlayerpoolAFragment poolAFrag = (PlayerpoolAFragment) fragment;
            poolAFrag.setOnFragmentAClickListener(this);
        }
        //super.onAttachFragment(fragment);
    }
}
