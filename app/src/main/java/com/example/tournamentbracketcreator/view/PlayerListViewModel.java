package com.example.tournamentbracketcreator.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.tournamentbracketcreator.application.BracketsApplication;
import com.example.tournamentbracketcreator.application.DataRepository;
import com.example.tournamentbracketcreator.entity.PlayerEntity;

import java.util.List;

public class PlayerListViewModel extends AndroidViewModel {
    private final DataRepository mRepository;
    private final MediatorLiveData<List<PlayerEntity>> mObservablePlayers;

    public PlayerListViewModel(@NonNull Application application) {
        super(application);
        mObservablePlayers = new MediatorLiveData<>();

        mRepository = ((BracketsApplication) application).getRepository();
        LiveData<List<PlayerEntity>> players = mRepository.getPlayers();
        mObservablePlayers.addSource(players, mObservablePlayers::setValue);
    }

    public LiveData<List<PlayerEntity>> getPlayers(){
        return mObservablePlayers;
    }

    public LiveData<List<PlayerEntity>> searchPlayers(String query){
        return mRepository.searchPlayers(query);
    }
}
