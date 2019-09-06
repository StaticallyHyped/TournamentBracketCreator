package com.example.tournamentbracketcreator.db;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.model.Data;

import java.util.ArrayList;

public class DatabaseInitializer {
    public static final String TAG = "DatabaseInitializer";
    private static final int DELAY_MILLIS = 500;

    public static ArrayList<String[]> tournPool = Data.getTournPoolList();

    public static void populateAsync(final AppDatabase db){
        Log.d(TAG, "populateAsync: starts");

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db){
        Log.d(TAG, "populateSync: starts");
        populateFromData(db);
    }

    private static PlayerEntity addPlayer(final AppDatabase db,
                                          final String id, final String name){
        Log.d(TAG, "addPlayer: starts");
        PlayerEntity player = new PlayerEntity();
        player.id = id;
        player.name = name;
        db.playerDao().insertPlayer(player);
        return player;
    }

    private static void populateFromData(AppDatabase db) {
        Log.d(TAG, "populateFromData: starts");
        db.playerDao().deleteAll();
        Log.d(TAG, "populateFromData: tournPool size: " + tournPool.size());
//        try {
            for (String[] i : tournPool) {
                String name = i[0];
                String id = i[1];

                PlayerEntity player1 = addPlayer(db, id, name);
//                Thread.sleep(DELAY_MILLIS);
                PlayerEntity player2 = addPlayer(db, id, name);
//                Thread.sleep(DELAY_MILLIS);
                PlayerEntity player3 = addPlayer(db, id, name);
//                Thread.sleep(DELAY_MILLIS);
                PlayerEntity player4 = addPlayer(db, id, name);
//                Thread.sleep(DELAY_MILLIS);
                PlayerEntity player5 = addPlayer(db, id, name);
//                Thread.sleep(DELAY_MILLIS);
                PlayerEntity player6 = addPlayer(db, id, name);
                PlayerEntity player7 = addPlayer(db, id, name);
                PlayerEntity player8 = addPlayer(db, id, name);

            }


    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final AppDatabase mDb;
        PopulateDbAsync(AppDatabase db){
            Log.d(TAG, "PopulateDbAsync: ");
            mDb = db;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: ");
            populateFromData(mDb);
            return null;
        }
    }
}
