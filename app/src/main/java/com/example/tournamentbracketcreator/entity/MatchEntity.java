package com.example.tournamentbracketcreator.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.tournamentbracketcreator.model.Match;

@Entity(tableName = "matches", foreignKeys = {
        @ForeignKey(entity = PlayerEntity.class, parentColumns = "id",
                childColumns = "playerOneId",
                onDelete = ForeignKey.CASCADE),

        @ForeignKey(entity = PlayerEntity.class, parentColumns = "id",
        childColumns = "playerTwoId",
        onDelete = ForeignKey.CASCADE)
})


public class MatchEntity implements Match {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public long id;

    @ColumnInfo(name = "playerOneId")
    public String playerOneId;

    @ColumnInfo(name = "playerTwoId")
    public String playerTwoId;

    public String playerOneScore;
    public String playerTwoScore;



    @Override
    public int getId() {
        return 0;
    }

    @Override
    public PlayerEntity getPlayerOne() {
        return null;
    }

    @Override
    public PlayerEntity getPlayerTwo() {
        return null;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public String getPlayerOneId() {
        return playerOneId;
    }

    public void setPlayerOneId(String playerOneId) {
        this.playerOneId = playerOneId;
    }

    public String getPlayerTwoId() {
        return playerTwoId;
    }

    public void setPlayerTwoId(String playerTwoId) {
        this.playerTwoId = playerTwoId;
    }

    public String getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(String playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public String getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(String playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }
}
