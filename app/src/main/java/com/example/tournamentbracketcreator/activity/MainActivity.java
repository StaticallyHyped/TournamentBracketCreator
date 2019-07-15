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

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private static final String LOGIN_FRAGMENT = "LoginFragment";

    public static final String TAG = "MainActivity";
    //private LoginFragment loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        /*ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);*/
        shouldDisplayHomeUp();


        Log.d(TAG, "onCreate: before initLoginFrag");
       // initializeLoginFragment();
        Log.d(TAG, "onCreate: afterInitLoginFrag");

    }
   /* @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }*/


   @Override
   public void onBackPressed() {
       Log.d(TAG, "onBackPressed: called");
       FragmentManager fragmentManager = getSupportFragmentManager();
       LoginFragment fragment = (LoginFragment) fragmentManager.
               findFragmentById(R.id.login_fragment_container);
       if((fragment == null) || fragment.canClose()){
           super.onBackPressed();
       } else {
           Log.d(TAG, "onBackPressed: fragment closed");
       }


   }

    /*@Override
    public boolean onSupportNavigateUp() {
       onBackPressed();
        return true;
    }*/

    @Override
    protected void onStop() {
        super.onStop();
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

    public void shouldDisplayHomeUp(){
       boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount()>0;
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
        int id = item.getItemId();
        switch (id){
            case R.id.home:
                Log.d(TAG, "onOptionsItemSelected: home button pressed");
                LoginFragment fragment =
                        (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.login_fragment_container);
                if(fragment.canClose()) {
                    return super.onOptionsItemSelected(item);
                } else {

                    return true;  // indicate we are handling this
                }
            case R.id.action_settings:
                Log.d(TAG, "onOptionsItemSelected: case:settings");
               return true;

                default:
                    return super.onOptionsItemSelected(item);

        }
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            finish();
        }*/

    }

    @Override
    public void onBackStackChanged() {
    shouldDisplayHomeUp();
    }
}
