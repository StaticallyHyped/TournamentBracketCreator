package com.example.tournamentbracketcreator.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.adapter.ViewPagerAdapter;
import com.example.tournamentbracketcreator.fragment.LoginFragment;
import com.example.tournamentbracketcreator.fragment.PlayerpoolBFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private static final String LOGIN_FRAGMENT = "LoginFragment";

    public static final String TAG = "MainActivity";
    FragmentManager fm;
    //private LoginFragment loginFragment;
    View tournPoolFragContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        fm = getSupportFragmentManager();
        fm.beginTransaction().addToBackStack(null).commit();
       // tournPoolFragContainer = findViewById(R.id.tourn_pool_frag_container);


        Log.d(TAG, "onCreate: afterInitLoginFrag");

    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: pressed!");
        if (getSupportFragmentManager().getBackStackEntryCount() > 2) {
            Log.d(TAG, "onBackPressed: backstack has this many entries:" +
                    getSupportFragmentManager().getBackStackEntryCount());
            getFragmentManager().popBackStack();
            super.onBackPressed();
        } else {
            Log.d(TAG, "onBackPressed: Fewer than 2");
            fm.beginTransaction().replace(R.id.login_fragment_container, new LoginFragment())
                    .commit();
            finish();
        }
    }


    public void onHomePressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 2) {
            Log.d(TAG, "onBackPressed: backstack has this many entries:" +
                    getSupportFragmentManager().getBackStackEntryCount());
            //getFragmentManager().popBackStack();
            super.onBackPressed();
        } else {
            Log.d(TAG, "onBackPressed: Fewer than 2");
            fm.beginTransaction().replace(R.id.login_fragment_container, new LoginFragment())
                    .commit();
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
