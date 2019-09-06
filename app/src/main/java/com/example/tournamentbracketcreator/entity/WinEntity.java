package com.example.tournamentbracketcreator.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.tournamentbracketcreator.model.Win;

import java.util.Date;

@Entity(tableName = "wins",
foreignKeys = {
        @ForeignKey(entity = PlayerEntity.class,
            parentColumns = "id",
            childColumns = "playerId",
                onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "playerId")
})

public class WinEntity implements Win {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "playerId")
    public int playerId;

    public String text;
    public Date postedAt;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public WinEntity(){

    }
    @Override
    public String getText() {
        return text;
    }

    @Override
    public Date getPostedAt() {
        return postedAt;
    }

    @Ignore
    public WinEntity(int id, int playerId, String text, Date postedAt){
        this.id = id;
        this.playerId = playerId;
        this.text = text;
        this.postedAt = postedAt;
    }
}
