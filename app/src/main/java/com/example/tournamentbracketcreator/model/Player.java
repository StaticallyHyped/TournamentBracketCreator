package com.example.tournamentbracketcreator.model;

public interface Player {
    String getId();
    String getName();
    String[] getWins();
    String[] getLosses();
    String getRating();
}
