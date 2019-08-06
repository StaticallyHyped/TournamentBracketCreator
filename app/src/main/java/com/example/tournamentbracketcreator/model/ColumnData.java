package com.example.tournamentbracketcreator.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ColumnData implements Serializable {

    private ArrayList<MatchData> matches;
    public ColumnData(ArrayList<MatchData> matches ) {
        this.matches = matches;
    }

    public ArrayList<MatchData> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<MatchData> matches) {
        this.matches = matches;
    }
}
