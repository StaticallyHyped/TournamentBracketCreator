package com.example.tournamentbracketcreator.model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerData implements Serializable {
    public static final String TAG = "PlayerData";

    //May need to update all instance variables as SimpleString
    private long m_Id;
    private final String mName;
    private final String[] mWins;
    private final String[] mLosses;
    private final String mRating;

    public PlayerData(long id, String name, @Nullable String[] wins, @Nullable String[] losses, @Nullable String rating) {
        this.m_Id = id;
        this.mName = name;
        this.mWins = wins;
        this.mLosses = losses;
        this.mRating = rating;
    }

    public static ArrayList<PlayerData> winnersRoundTwoData = new ArrayList<>();
    public static ArrayList<PlayerData> getWinnersRoundTwoData(){
        return winnersRoundTwoData;
    }


    public long getM_Id() {
        return m_Id;
    }

    public void setM_Id(long m_Id) {
        this.m_Id = m_Id;
    }

    public String getmName() {
        return mName;
    }

    public String[] getmWins() {
        return mWins;
    }

    public String[] getmLosses() {
        return mLosses;
    }

    public String getmRating() {
        return mRating;
    }
}
