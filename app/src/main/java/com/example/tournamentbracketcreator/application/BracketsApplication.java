package com.example.tournamentbracketcreator.application;

import android.app.Application;
import android.util.Log;

import com.example.tournamentbracketcreator.db.AppDatabase;
import com.example.tournamentbracketcreator.db.AppExecutors;

public class BracketsApplication extends Application {
    public static final String TAG = "BracketsApplication";
    private AppExecutors mAppExecutors;

    private int screenHeight;
    private static BracketsApplication applicationInstance;

    @Override
    public void onCreate(){
        Log.d(TAG, "onCreate: starts");
        super.onCreate();
        applicationInstance = this;
        mAppExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository(){
        return DataRepository.getInstance(getDatabase());
    }

    public static synchronized BracketsApplication getInstance(){
        return applicationInstance;
    }

    public int getScreenHeight(){
        return screenHeight;
    }
    public void setScreenHeight(int screenHeight){
        this.screenHeight = screenHeight;
    }
}
