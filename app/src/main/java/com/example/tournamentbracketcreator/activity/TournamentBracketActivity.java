package com.example.tournamentbracketcreator.activity;

import android.os.Bundle;

import com.example.tournamentbracketcreator.fragment.BracketsFragment;
import com.example.tournamentbracketcreator.application.BracketsApplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.util.Log;

import com.example.tournamentbracketcreator.R;

public class TournamentBracketActivity extends AppCompatActivity {
    public static final String TAG = "TournamentBracketActivity";
    private BracketsFragment bracketFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_bracket);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initBracketsFragment();

    }
    private void initBracketsFragment(){
        Log.d(TAG, "initBracketsFragment: starts");
        bracketFragment = new BracketsFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.bracket_container, bracketFragment, "brackets_frag");
        transaction.commit();
        manager.executePendingTransactions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setScreenSize();
    }

    private void setScreenSize(){
        Log.d(TAG, "setScreenSize: starts");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        BracketsApplication.getInstance().setScreenHeight(height);
    }
}
