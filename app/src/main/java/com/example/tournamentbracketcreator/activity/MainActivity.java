package com.example.tournamentbracketcreator.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.adapter.ViewPagerAdapter;
import com.example.tournamentbracketcreator.fragment.LoginFragment;
import com.example.tournamentbracketcreator.fragment.PlayerpoolAFragment;
import com.example.tournamentbracketcreator.fragment.PlayerpoolBFragment;
import com.example.tournamentbracketcreator.fragment.StartnavFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    public static final String TAG = "MainActivity";
    FragmentManager fm;
    public LoginFragment loginFragment = new LoginFragment();
    public Button startTournBtn;
    public Button startNavBtn;
    StartnavFragment snf;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportFragmentManager().addOnBackStackChangedListener(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTran = fm.beginTransaction();
        fragmentTran.replace(R.id.login_fragment_container, loginFragment, "login_frag")
                .addToBackStack("login_frag").commit();
        //START innerClass ViewPager experiment




        /*//START goToStartNav experiment
        StartnavFragment snf = (StartnavFragment) getSupportFragmentManager().
                findFragmentById(R.id.login_fragment);
        startNavBtn = findViewById(R.id.fragment_login_usernameTE);
        //snf = getSupportFragmentManager().findFragmentById(R.id.login_fragment);
        //tournPoolFragContainer = findViewById(R.id.tourn_pool_frag_container);
        goToStartNav(startNavBtn);
        //startTournActivity(startTournBtn, snf);*/
        Log.d(TAG, "onCreate: afterInitLoginFrag");
    }


    /*private void startTournActivity(Button button, final Fragment fragmentA) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewPager = getActivity().findViewById(R.id.tourn_pool_pager);

                FragmentTransaction fragmentTransaction =
                        fm.beginTransaction();
                //fragmentTransaction.hide(fragment);
                fragmentTransaction.replace(R.id.login_fragment_container, fragmentA)
                        .addToBackStack(null);
                fragmentTransaction.commit();
                *//*FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.poolb_container, fragmentB).addToBackStack("frag_poolb").commit();*//*
            }
        });

    }*/
    private void goToStartNav(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartnavFragment startnavFragment = new StartnavFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.login_fragment_container, startnavFragment)
                        .commit();
            }
        });



    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: pressed!");
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            Log.d(TAG, "onBackPressed: backstack has this many entries:" +
                    getSupportFragmentManager().getBackStackEntryCount());
            getFragmentManager().popBackStack();
            super.onBackPressed();
        } else {
            Log.d(TAG, "onBackPressed: backstack has this many entries: " +
                    getSupportFragmentManager().getBackStackEntryCount());
            Log.d(TAG, "onBackPressed: Fewer than 2");
//            fm.beginTransaction().replace(R.id.login_fragment_container, new LoginFragment())
//                    .commit();
            finish();
        }
    }


    public void onHomePressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            Log.d(TAG, "onHomePressed: backstack has this many entries:" +
                    getSupportFragmentManager().getBackStackEntryCount());
            //getFragmentManager().popBackStack();
            super.onBackPressed();
        } else {
            Log.d(TAG, "onHomePressed: backstack has this many entries: " +
                    getSupportFragmentManager().getBackStackEntryCount());
            Log.d(TAG, "onHomePressed: Fewer than 2");
            /*fm.beginTransaction().replace(R.id.login_fragment_container, new LoginFragment())
                    .commit();*/
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: Start");
        super.onResume();
        setScreenSize();
    }

    public void shouldDisplayHomeUp() {
        boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canGoBack);
    }

    private void setScreenSize() {
        Log.d(TAG, "setScreenSize: Start");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        // TODO create BracketsApplication class
        //  BracketsApplication.getInstance().setScreenHeight(height);
    }


    //TODO add toolbar (?)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG, "onOptionsItemSelected: item selected");
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Log.d(TAG, "onOptionsItemSelected: home button pressed");
                onHomePressed();
                Log.d(TAG, "onOptionsItemSelected: item" + item);
                return true;
            case R.id.action_settings:
                Log.d(TAG, "onOptionsItemSelected: case:settings");
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }
}
