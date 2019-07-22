package com.example.tournamentbracketcreator.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.LoginFragment;
import com.example.tournamentbracketcreator.model.MySQLConnector;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    public static final String TAG = "MainActivity";
    public LoginFragment loginFragment = new LoginFragment();
    private AWSAppSyncClient mAWSAppSyncClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();
        
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTran = fm.beginTransaction();
        fragmentTran.replace(R.id.login_fragment_container, loginFragment, "login_frag")
                .addToBackStack("login_frag").commit();
        
        try {
            Log.d(TAG, "onCreate: trying to connect");
            MySQLConnector.makeConnection();
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "onCreate: failed to connect");
        }

        Log.d(TAG, "onCreate: afterInitLoginFrag");
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
