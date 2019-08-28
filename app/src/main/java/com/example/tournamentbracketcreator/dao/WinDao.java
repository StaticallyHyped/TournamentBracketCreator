package com.example.tournamentbracketcreator.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.tournamentbracketcreator.entity.WinEntity;

import java.util.List;

@Dao
public interface WinDao {
    @Query("SELECT * FROM wins WHERE playerId = :playerId")
    LiveData<List<WinEntity>> loadWins(int playerId);

    @Query("SELECT * FROM wins WHERE playerId = :playerId")
    List<WinEntity> loadWinsSync(int playerId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WinEntity> wins);
}
