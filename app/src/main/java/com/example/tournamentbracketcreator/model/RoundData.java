package com.example.tournamentbracketcreator.model;

import java.io.Serializable;

public class RoundData implements Serializable {
    private static final String TAG = "RoundData";

    private PlayerData playerOne;
    private PlayerData playerTwo;

    private int height;

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public RoundData(PlayerData playerOne, PlayerData playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public PlayerData getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(PlayerData playerOne) {
        this.playerOne = playerOne;
    }

    public PlayerData getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(PlayerData playerTwo) {
        this.playerTwo = playerTwo;
    }
}
