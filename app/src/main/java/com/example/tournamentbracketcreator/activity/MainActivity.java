package com.example.tournamentbracketcreator.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {
    private static final String LOGIN_FRAGMENT = "LoginFragment";

    public static final String TAG = "MainActivity";
    private LoginFragment loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, "onCreate: before initLoginFrag");
        initializeLoginFragment();
        Log.d(TAG, "onCreate: afterInitLoginFrag");

    }

    private void initializeLoginFragment() {
        Log.d(TAG, "initializeLoginFragment: Start initLogFrag");
        loginFragment = new LoginFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_login_constraintLayout, loginFragment, "login_home_fragment");
        transaction.commit();
        manager.executePendingTransactions();
        Log.d(TAG, "initializeLoginFragment: Exit initLogFrag");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: Start");
        super.onResume();
        setScreenSize();
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
    /*@Override
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
