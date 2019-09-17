package com.example.tournamentbracketcreator.db;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tournamentbracketcreator.entity.MatchEntity;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.model.Data;
import com.example.tournamentbracketcreator.model.Match;

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

    private static MatchEntity addMatch(final AppDatabase db, final int id,
                                        final String playerOneId, final String playerTwoId){
        MatchEntity match = new MatchEntity();
        match.id = id;
        match.playerOneId = playerOneId;
        match.playerTwoId = playerTwoId;
        db.matchDao().insertMatch(match);
        return match;
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

        db.playerDao().deleteAll();
        db.matchDao().deleteAll();

        PlayerEntity player1 = null;
        PlayerEntity player2 = null;
        PlayerEntity player3 = null;
        PlayerEntity player4 = null;
        PlayerEntity player5 = null;
        PlayerEntity player6 = null;
        PlayerEntity player7 = null;
        PlayerEntity player8 = null;


        // This loops through the ArrayList tournPool
        for (int e = 0; e < tournPool.size(); e++){
            Log.d(TAG, "populateFromData: tournPool size: " + tournPool.size());
            // create a String[] object then loop through the String[] to get id[1] and name[0]
            // matches are created as players are added
            // the case blocks prevent matches from being created if no/insufficient players are present
            
            switch (e){
                case (0):
                    String[] p1 = tournPool.get(e);
                    player1 = addPlayer(db, p1[1], p1[0]);
                    Log.d(TAG, "populateFromData: p1 added");
                    break;
                case (1):
                    String[] p2 = tournPool.get(e);
                    player2 = addPlayer(db, p2[1], p2[0]);
                    MatchEntity match1 = addMatch(db, 1 ,player1.getId(), player2.getId());
                    Log.d(TAG, "populateFromData: p2 added");
                    break;
                case (2):
                    String[] p3 = tournPool.get(e);
                    player3 = addPlayer(db, p3[1], p3[0]);
                    Log.d(TAG, "populateFromData: p3 added");
                    break;
                case (3):
                    String[] p4 = tournPool.get(e);
                    player4 = addPlayer(db, p4[1], p4[0]);
                    MatchEntity match2 = addMatch(db, 2 ,player3.getId(), player4.getId());
                    Log.d(TAG, "populateFromData: p4 added");
                    break;
                case (4):
                    String[] p5 = tournPool.get(e);
                    player5 = addPlayer(db, p5[1], p5[0]);
                    Log.d(TAG, "populateFromData: p5 added");
                    break;
                case (5):
                    String[] p6 = tournPool.get(e);
                    player6 = addPlayer(db, p6[1], p6[0]);
                    MatchEntity match3 = addMatch(db, 3 ,player5.getId(), player6.getId());
                    break;
                case (6):
                    String[] p7 = tournPool.get(e);
                    player7 = addPlayer(db, p7[1], p7[0]);
                    break;
                case (7):
                    String[] p8 = tournPool.get(e);
                    player8 = addPlayer(db, p8[1], p8[0]);
                    MatchEntity match4 = addMatch(db, 4 ,player7.getId(), player8.getId());
                    break;
            }
        }
       /* MatchEntity match5 = addMatch(db, 5, "123456", null, "0", "0");
        MatchEntity match6 = addMatch(db, 6, null, null, "0", "0");
        MatchEntity match7 = addMatch(db, 7, null, null, "0", "0");
*/
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
