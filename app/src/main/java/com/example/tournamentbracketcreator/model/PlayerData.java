package com.example.tournamentbracketcreator.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerData implements Serializable {
    public static final String TAG = "PlayerData";

    //May need to update all instance variables as SimpleString
    private String mId;
    private String mName;


    public PlayerData(String id, String name) {
        this.mId = id;
        this.mName = name;

    }

    public static ArrayList<PlayerData> winnersRoundTwoData = new ArrayList<>();

    public static ArrayList<PlayerData> getWinnersRoundTwoData() {
        return winnersRoundTwoData;
    }


    public String getId() {
        return mId;
    }

    public void setId(String m_Id) {
        this.mId = m_Id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name){
        this.mName = name;
    }

}
