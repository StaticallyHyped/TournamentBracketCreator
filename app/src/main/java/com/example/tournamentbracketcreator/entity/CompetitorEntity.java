package com.example.tournamentbracketcreator.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.tournamentbracketcreator.model.Competitor;

@Entity(tableName = "competitors")
public class CompetitorEntity implements Competitor {
    @PrimaryKey
    private int id;
    private String name;
    private String score;


    @Override
    public int getId() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return null;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String getScore() {
        return null;
    }

    public void setScore(String score){
        this.score = score;
    }

    public CompetitorEntity(){

    }

    @Ignore
    public CompetitorEntity(int id, String name, String score){
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
