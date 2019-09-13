package com.example.tournamentbracketcreator.view;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModel;

import com.example.tournamentbracketcreator.application.BracketsApplication;
import com.example.tournamentbracketcreator.application.DataRepository;
import com.example.tournamentbracketcreator.db.AppDatabase;
import com.example.tournamentbracketcreator.db.DatabaseInitializer;
import com.example.tournamentbracketcreator.entity.MatchEntity;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.entity.WinEntity;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    public static final String TAG = "PlayerViewModel";

    public final LiveData<List<PlayerEntity>> mObservablePlayers;
    public final LiveData<List<MatchEntity>> mObservableMatches;
    public ObservableField<PlayerEntity> player = new ObservableField<>();
    private AppDatabase mDb;

    public PlayerViewModel(Application application){
        super(application);
        Log.d(TAG, "PlayerViewModel: constructor, before createDb();");
        createDb();
        Log.d(TAG, "PlayerViewModel: constructor, after createDb();");
        mObservablePlayers = mDb.playerDao().loadAllPlayers();
        mObservableMatches = mDb.matchDao().loadAllMatches();
    }

    public void createDb(){
        Log.d(TAG, "createDb: starts");
        mDb = AppDatabase.getDatabase(this.getApplication());
        DatabaseInitializer.populateAsync(mDb);
    }
    //Expose the LiveData Wins query so the UI can observe it

    public LiveData<List<PlayerEntity>> getObservablePlayers(){
        return mObservablePlayers;
    }
    public void setPlayer(PlayerEntity player) {
        this.player.set(player);
    }

}
