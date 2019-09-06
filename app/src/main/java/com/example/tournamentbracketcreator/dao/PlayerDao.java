package com.example.tournamentbracketcreator.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Dao;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface PlayerDao {
    @Query("SELECT * FROM players")
    LiveData<List<PlayerEntity>> loadAllPlayers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PlayerEntity> players);

    @Insert(onConflict = IGNORE)
    void insertPlayer(PlayerEntity playerEntity);

    @Query("SELECT * FROM players WHERE id = :playerId")
    LiveData<PlayerEntity> loadPlayer(int playerId);

    @Query("SELECT * FROM players WHERE id = :playerId")
    PlayerEntity loadPlayerSync(int playerId);

    @Query("SELECT players.* FROM players JOIN playersFts ON (players.id = playersFts.rowid) "
            + "WHERE playersFts MATCH :query")
    LiveData<List<PlayerEntity>> searchAllPlayers(String query);

    @Query("DELETE FROM players")
    void deleteAll();
}
