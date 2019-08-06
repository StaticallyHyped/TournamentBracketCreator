package com.example.tournamentbracketcreator.model;

import android.util.Log;

import com.apollographql.apollo.api.internal.Utils;

import java.util.ArrayList;

public class Data {
    private String name;

    public Data(String name) {
       // this.name = Utils.checkNotNull(name, "name == null");
        this.name = name;
    }

    public static ArrayList<String> tournPoolList = new ArrayList<>();
    public static final String TAG = "Data";

    public static ArrayList<String> getTournPoolList() {
        Log.d(TAG, "getTournPoolList: " + tournPoolList.toString());
        return tournPoolList;
    }

    public static void setTournPoolList(ArrayList<String> tournPoolList) {
        Data.tournPoolList = tournPoolList;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return this.name.toString();
    }
}
