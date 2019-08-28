package com.example.tournamentbracketcreator.db;

import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.entity.WinEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestData {
    //static final PlayerEntity PLAYER_ENTITY = new PlayerEntity(1, "name", 3);
    static final PlayerEntity PLAYER_ENTITY = new PlayerEntity(1, "name");
    static final PlayerEntity PLAYER_ENTITY2 = new PlayerEntity(2, "name2");

    static final List<PlayerEntity> PLAYERS = Arrays.asList(PLAYER_ENTITY, PLAYER_ENTITY2);

//    static final WinEntity WIN_ENTITY = new WinEntity(1, PLAYER_ENTITY.getId(), "7", new Date());
    static final WinEntity WIN_ENTITY = new WinEntity(1, PLAYER_ENTITY.getId(), "8", new Date());

    static final WinEntity WIN_ENTITY2 = new WinEntity(2,
            PLAYER_ENTITY2.getId(), "14", new Date());

    static final List<WinEntity> WINS = Arrays.asList(WIN_ENTITY, WIN_ENTITY2);
}
