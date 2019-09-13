package com.example.tournamentbracketcreator.model;

import com.example.tournamentbracketcreator.entity.PlayerEntity;

public interface Match {
    int getId();
    PlayerEntity getPlayerOne();
    PlayerEntity getPlayerTwo();
}
