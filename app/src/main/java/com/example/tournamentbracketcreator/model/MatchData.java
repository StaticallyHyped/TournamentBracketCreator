package com.example.tournamentbracketcreator.model;

import java.io.Serializable;

public class MatchData implements Serializable {

//    private CompetitorData competitorOne;
//    private CompetitorData competitorTwo;

    private PlayerData playerOne;
    private PlayerData playerTwo;
    private int height;
    private long id;

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    //TODO update MatchData to take PlayerData as parameters
    public MatchData(long id, PlayerData playerOne, PlayerData playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.id = id;

    }

    public PlayerData getCompetitorOne() {
        return playerOne;
    }

    //TODO setCompetitorOne from poolRVData, by name
    public void setCompetitorOne(PlayerData playerOne) {
        this.playerOne = playerOne;
    }

    public PlayerData getCompetitorTwo() {
        return playerTwo;
    }
    //TODO setCompetitorTwo from poolRVData, by name
    public void setCompetitorTwo(PlayerData playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
