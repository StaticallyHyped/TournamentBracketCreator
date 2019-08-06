package com.example.tournamentbracketcreator.model;

import java.io.Serializable;

public class CompetitorData implements Serializable {

    private String name;
    private String score;

    public CompetitorData(String name, String score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore(){
        return score;
    }


}
