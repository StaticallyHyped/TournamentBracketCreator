package com.example.tournamentbracketcreator.model;

import android.util.Log;

import com.apollographql.apollo.api.internal.Utils;

import java.util.ArrayList;

public class Data {
    private String name;
    private String id;

    public Data(String name, String id) {
       // this.name = Utils.checkNotNull(name, "name == null");
        this.name = name;
        this.id = id;
    }

    public static ArrayList<String[]> tournPoolList = new ArrayList<>();
    public static final String TAG = "Data";

    public static ArrayList<String[]> getTournPoolList() {
        Log.d(TAG, "getTournPoolList: " + tournPoolList.toString());
        return tournPoolList;
    }

    public static void setTournPoolList(ArrayList<String[]> tournPoolList) {
        Data.tournPoolList = tournPoolList;
    }
    public String getName(){
        return this.name;
    }


}
