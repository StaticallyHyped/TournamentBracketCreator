package com.example.tournamentbracketcreator.contracts;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class PlayerContract {

    /*public static final String PLAYERS_TABLE_NAME = "Players";

    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String PLAYER_NAME = "Name";
        public static final String PLAYER_WINS = "Wins";
        public static final String PLAYER_LOSSES = "Losses";
        public static final String PLAYER_RATING = "Rating";

        private Columns(){

        }

    }
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, PLAYERS_TABLE_NAME);
    public static final String CONTENT_TYPE = "INSERT CONTENT TYPE HERE." + CONTENT_AUTHORITY + "."
            + PLAYERS_TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE = "INSERT CONTENT TYPE HERE." + CONTENT_AUTHORITY + "."
            + PLAYERS_TABLE_NAME;

    static Uri buildPlayerUri (long playerId){
        return ContentUris.withAppendedId(CONTENT_URI, playerId);
    }
    static long getCourseId(Uri uri){
        return ContentUris.parseId(uri);
    }*/
}
