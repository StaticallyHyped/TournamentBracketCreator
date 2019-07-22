package com.example.tournamentbracketcreator.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tournamentbracketcreator.contracts.PlayerContract;

public class AppDatabase extends SQLiteOpenHelper {

    public static final String TAG = "AppDatabase";
    public static final String DATABASE_NAME = "U04n6X";
    public static final int DATABASE_VERSION = 1;

    private static AppDatabase instance = null;

    private AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static AppDatabase getInstance(Context context){
        if (instance == null){
            instance = new AppDatabase(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String playerSQL;
        playerSQL = "CREATE TABLE IF NOT EXISTS " + PlayerContract.PLAYERS_TABLE_NAME + " ("
                + PlayerContract.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
                + PlayerContract.Columns.PLAYER_NAME + " TEXT NOT NULL, "
                + PlayerContract.Columns.PLAYER_WINS + " TEXT, "
                + PlayerContract.Columns.PLAYER_LOSSES + " TEXT);";
        db.execSQL(playerSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
