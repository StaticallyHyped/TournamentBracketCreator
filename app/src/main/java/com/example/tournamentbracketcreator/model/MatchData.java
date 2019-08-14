package com.example.tournamentbracketcreator.model;

import java.io.Serializable;

public class MatchData implements Serializable {

    private CompetitorData competitorOne;
    private CompetitorData competitorTwo;
    private int height;

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    //TODO update MatchData to take PlayerData as parameters
    public MatchData(CompetitorData competitorOne, CompetitorData competitorTwo) {
        this.competitorOne = competitorOne;
        this.competitorTwo = competitorTwo;

    }

    public CompetitorData getCompetitorOne() {
        return competitorOne;
    }

    //TODO setCompetitorOne from poolRVData, by name
    public void setCompetitorOne(CompetitorData competitorOne) {
        this.competitorOne = competitorOne;
    }

    public CompetitorData getCompetitorTwo() {
        return competitorTwo;
    }
    //TODO setCompetitorTwo from poolRVData, by name
    public void setCompetitorTwo(CompetitorData competitorTwo) {
        this.competitorTwo = competitorTwo;
    }
}
