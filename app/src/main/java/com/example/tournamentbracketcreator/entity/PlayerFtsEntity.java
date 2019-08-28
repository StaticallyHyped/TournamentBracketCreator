package com.example.tournamentbracketcreator.entity;

import androidx.room.Entity;
import androidx.room.Fts4;


@Entity(tableName = "playersFts")
@Fts4(contentEntity = PlayerEntity.class)
public class PlayerFtsEntity {
    private String name;

    public PlayerFtsEntity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
