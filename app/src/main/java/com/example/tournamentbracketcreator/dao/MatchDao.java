package com.example.tournamentbracketcreator.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tournamentbracketcreator.entity.MatchEntity;
import com.example.tournamentbracketcreator.model.Match;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface MatchDao {

    @Query("SELECT * FROM matches")
    LiveData<List<MatchEntity>> loadAllMatches();

    @Query("SELECT * FROM matches where id = :id")
    MatchEntity loadMatchById(int id);

    /*@Query("SELECT * FROM matches " +
//            "INNER JOIN players ON players.id = matches.playerOneId AND matches.playerTwoId " +
            "INNER JOIN players ON players.id = matches.playerOneId AND matches.playerTwoId " +
            "WHERE players.id LIKE :playerOneId AND :playerTwoId")
    LiveData<List<MatchEntity>> loadMatchesByPlayers(String playerOneId, String playerTwoId);*/

    @Insert(onConflict = IGNORE)
    void insertMatch(MatchEntity match);

    @Query("DELETE FROM matches")
    void deleteAll();
}
