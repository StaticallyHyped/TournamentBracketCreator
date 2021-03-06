package com.example.tournamentbracketcreator.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.example.tournamentbracketcreator.model.Player;

@Entity(tableName = "players")
public class PlayerEntity implements Player {
    @PrimaryKey
    @NonNull
    public String id;
    @NonNull
    public String name;


    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getWins() {
        return new String[0];
    }

    @Override
    public String[] getLosses() {
        return new String[0];
    }

    @Override
    public String getRating() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerEntity(){

    }
    //used in TestData.java
    @Ignore
    public PlayerEntity(String id, String name) {
        this.id = id;
        this.name = name;

    }
    //used in TestData.java
    public PlayerEntity(Player player){
        this.id = player.getId();
        this.name = player.getName();

    }

}
