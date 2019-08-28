package com.example.tournamentbracketcreator.model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class CompetitorData implements Serializable {

    private long m_Id;
    private String name;
    private String score;

    public CompetitorData(long id, String name, String score){
        this.m_Id = id;
        this.name = name;
        this.score = score;
    }
    public static ArrayList<CompetitorData> winnersRoundTwoData = new ArrayList<>();

    public static ArrayList<CompetitorData> getWinnersRoundTwoData(){
        return winnersRoundTwoData;
    }

    public String getName() {
        return name;
    }
    /*static CompetitorData getInstance(Context context){
        if ()
    }*/

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore(){
        return score;
    }

    public long getId() {
        return m_Id;
    }

    public void setId(long m_Id){
        this.m_Id = m_Id;
    }


}
