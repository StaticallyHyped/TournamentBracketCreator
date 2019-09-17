package com.example.tournamentbracketcreator.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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
    public int id;

    @ColumnInfo(name = "playerOneId")
    public String playerOneId;

    @ColumnInfo(name = "playerTwoId")
    public String playerTwoId;


    @Override
    public int getId() {
        return id;
    }

    /*@Override
    public PlayerEntity getPlayerOne() {
        return null;
    }

    @Override
    public PlayerEntity getPlayerTwo() {
        return null;
    }*/

    public void setId(int id) {
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


    public MatchEntity(){

    }

    @Ignore
    public MatchEntity(String playerOneId, String playerTwoId){

        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
    }

    /*public MatchEntity(Match match){
        this.id = match.getId();
        this.playerOneId = match.getPlayerOneId();
        this.playerTwoId = match.getPlayerTwoId();
    }*/
}
