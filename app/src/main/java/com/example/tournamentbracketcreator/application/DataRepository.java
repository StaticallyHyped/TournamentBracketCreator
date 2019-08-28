package com.example.tournamentbracketcreator.application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.tournamentbracketcreator.db.AppDatabase;
import com.example.tournamentbracketcreator.entity.CompetitorEntity;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.entity.WinEntity;
import com.example.tournamentbracketcreator.model.Player;

import java.util.List;

public class DataRepository {
    private static DataRepository sInstance;

    private final AppDatabase mDatabase;

    private MediatorLiveData<List<PlayerEntity>> mObservablePlayers;
    private MediatorLiveData<List<CompetitorEntity>> mObservableCompetitors;


    /*private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservablePlayers = new MediatorLiveData<>();
        mObservablePlayers.addSource(mDatabase.playerDao().loadAllPlayers(),
                playerEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null){
                        mObservablePlayers.postValue(playerEntities);
                    }
                });
    }*/

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableCompetitors = new MediatorLiveData<>();
        mObservableCompetitors.addSource(mDatabase.competitorDao().loadAllCompetitors(),
                competitorEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null){
                        mObservableCompetitors.postValue(competitorEntities);
                    }
                });
    }
    public static DataRepository getInstance(final AppDatabase database){
        if (sInstance == null){
            synchronized (DataRepository.class) {
                if (sInstance == null){
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<PlayerEntity>> getPlayers() {
        return mObservablePlayers;
    }

    public LiveData<PlayerEntity> loadPlayer(final int playerId) {
        return mDatabase.playerDao().loadPlayer(playerId);
    }

    public LiveData<List<CompetitorEntity>> getCompetitors(){
        return mObservableCompetitors;
    }
    public LiveData<CompetitorEntity> loaderCompetitor(final int competitorId){
        return mDatabase.competitorDao().loadCompetitor(competitorId);
    }

    /*public LiveData<List<CompetitorEntity>> searchCompetitors(String query){
        return mDatabase.competitorDao().searchAllCompetitors(query);
    }*/

    public LiveData<List<WinEntity>> loadWins(final int playerId){
        return mDatabase.winDao().loadWins(playerId);
    }
    public LiveData<List<PlayerEntity>> searchPlayers(String query){
        return mDatabase.playerDao().searchAllPlayers(query);
    }
}
