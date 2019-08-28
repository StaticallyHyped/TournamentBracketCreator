package com.example.tournamentbracketcreator.model;

import java.util.ArrayList;

public interface Player {
    int getId();
    String getName();
    String[] getWins();
    String[] getLosses();
    String getRating();
}
