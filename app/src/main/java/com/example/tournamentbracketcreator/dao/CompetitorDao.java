package com.example.tournamentbracketcreator.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tournamentbracketcreator.entity.CompetitorEntity;
import com.example.tournamentbracketcreator.entity.PlayerEntity;

import java.util.List;

@Dao
public interface CompetitorDao {
    @Query("SELECT * FROM competitors")
    LiveData<List<CompetitorEntity>> loadAllCompetitors();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CompetitorEntity> competitors);

    @Query("select * from competitors where id = :competitorId")
    LiveData<CompetitorEntity> loadCompetitor(int competitorId);

    @Query("select * from competitors where id = :competitorId")
    CompetitorEntity loadCompetitorSync(int competitorId);

    /*@Query("SELECT competitors.* FROM competitors JOIN competitorsFts ON (players.id = playersFts.rowid) "
            + "WHERE playersFts MATCH :query")*/

//    LiveData<List<CompetitorEntity>> searchAllCompetitors(String query);
}
