package com.example.tournamentbracketcreator.model;

import com.example.tournamentbracketcreator.entity.PlayerEntity;

import java.io.Serializable;

public class MatchData implements Serializable {

    private PlayerEntity playerOne;
    private PlayerEntity playerTwo;
    private int height;
    private long id;

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public MatchData(long id, PlayerEntity playerOne, PlayerEntity playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.id = id;

    }

    public PlayerEntity getCompetitorOne() {
        return playerOne;
    }

    public void setCompetitorOne(PlayerEntity playerOne) {
        this.playerOne = playerOne;
    }

    public PlayerEntity getCompetitorTwo() {
        return playerTwo;
    }

    public void setCompetitorTwo(PlayerEntity playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
