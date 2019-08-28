package com.example.tournamentbracketcreator.view;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModel;

import com.example.tournamentbracketcreator.application.BracketsApplication;
import com.example.tournamentbracketcreator.application.DataRepository;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.entity.WinEntity;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    public static final String TAG = "PlayerViewModel";

    private final LiveData<PlayerEntity> mObservablePlayer;
    public ObservableField<PlayerEntity> player = new ObservableField<>();
    private final int mPlayerId;
    private final LiveData<List<WinEntity>> mObservableWins;

    public PlayerViewModel(@NonNull Application application, DataRepository repository,
                           final int playerId){
        super(application);
        mPlayerId = playerId;
        mObservablePlayer = repository.loadPlayer(mPlayerId);
        mObservableWins = repository.loadWins(mPlayerId);
    }

    //Expose the LiveData Wins query so the UI can observe it
    public LiveData<List<WinEntity>> getWins(){
        return mObservableWins;
    }
    public LiveData<PlayerEntity> getObservablePlayer(){
        return mObservablePlayer;
    }

    public void setPlayer(PlayerEntity player) {
        this.player.set(player);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;
        private final int mPlayerId;
        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int playerId){
            mApplication = application;
            mPlayerId = playerId;
            mRepository = ((BracketsApplication) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new PlayerViewModel(mApplication, mRepository, mPlayerId);
        }

    }
}
