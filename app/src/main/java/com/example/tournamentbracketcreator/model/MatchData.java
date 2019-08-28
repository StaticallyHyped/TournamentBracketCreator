package com.example.tournamentbracketcreator.model;

import java.io.Serializable;

public class MatchData implements Serializable {

//    private CompetitorData competitorOne;
//    private CompetitorData competitorTwo;

    private CompetitorData playerOne;
    private CompetitorData playerTwo;
    private int height;
    private long id;

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    //TODO update MatchData to take PlayerData as parameters
    public MatchData(long id, CompetitorData playerOne, CompetitorData playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.id = id;

    }

    public CompetitorData getCompetitorOne() {
        return playerOne;
    }

    //TODO setCompetitorOne from poolRVData, by name
    public void setCompetitorOne(CompetitorData playerOne) {
        this.playerOne = playerOne;
    }

    public CompetitorData getCompetitorTwo() {
        return playerTwo;
    }
    //TODO setCompetitorTwo from poolRVData, by name
    public void setCompetitorTwo(CompetitorData playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
