package com.example.tournamentbracketcreator.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tournamentbracketcreator.db.AppDatabase;
import com.example.tournamentbracketcreator.db.DatabaseInitializer;
import com.example.tournamentbracketcreator.entity.MatchEntity;

import java.util.List;

public class MatchViewModel extends AndroidViewModel {
    public static final String TAG = "MatchViewModel";

    public final LiveData<List<MatchEntity>> mObservableMatches;
    private AppDatabase mDb;

    public MatchViewModel(@NonNull Application application) {
        super(application);
        createDb();
        mObservableMatches = mDb.matchDao().loadAllMatches();
    }

    public void createDb(){
        mDb = AppDatabase.getDatabase(this.getApplication());
        DatabaseInitializer.populateAsync(mDb);
    }


}
