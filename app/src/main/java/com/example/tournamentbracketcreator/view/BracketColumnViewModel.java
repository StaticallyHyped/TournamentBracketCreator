package com.example.tournamentbracketcreator.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tournamentbracketcreator.application.BracketsApplication;
import com.example.tournamentbracketcreator.application.DataRepository;
import com.example.tournamentbracketcreator.entity.CompetitorEntity;

import java.util.List;

public class BracketColumnViewModel extends AndroidViewModel {
    private final DataRepository mRepository;
    private final MediatorLiveData<List<CompetitorEntity>> mObservableCompetitors;


    public BracketColumnViewModel(@NonNull Application application) {
        super(application);
        mObservableCompetitors = new MediatorLiveData<>();

        mRepository = ((BracketsApplication) application).getRepository();
        LiveData<List<CompetitorEntity>> competitors = mRepository.getCompetitors();
//        LiveData<List<CompetitorEntity>> competitors = mRepository.getPlayers();
    }
    // TODO: Implement the ViewModel
}
