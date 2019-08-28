package com.example.tournamentbracketcreator.db;


import android.database.sqlite.SQLiteConstraintException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import com.example.tournamentbracketcreator.LiveDataTestUtil;
import com.example.tournamentbracketcreator.dao.PlayerDao;
import com.example.tournamentbracketcreator.dao.WinDao;
import com.example.tournamentbracketcreator.entity.WinEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.example.tournamentbracketcreator.db.TestData.PLAYERS;
import static com.example.tournamentbracketcreator.db.TestData.WINS;
import static com.example.tournamentbracketcreator.db.TestData.WIN_ENTITY;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class WinDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private AppDatabase mDatabase;
    private WinDao mWinDao;
    private PlayerDao mPlayerDao;

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        mWinDao = mDatabase.winDao();
        mPlayerDao = mDatabase.playerDao();
    }
    @After
    public void closeDb() throws Exception{
        mDatabase.close();
    }

    @Test
    public void getWinsWhenNoWinInserted() throws InterruptedException {
        List<WinEntity> wins = LiveDataTestUtil.getValue(mWinDao.loadWins(WIN_ENTITY.getPlayerId()));

        assertTrue(wins.isEmpty());
    }

    @Test
    public void cantInsertWinWithoutPlayer() throws InterruptedException {
        try {
            mWinDao.insertAll(WINS);
            fail("SQLiteConstraintException expected");
        } catch (SQLiteConstraintException ignored){

        }
    }

    @Test
    public void getWinsAfterInserted() throws InterruptedException {
        mPlayerDao.insertAll(PLAYERS);
        mWinDao.insertAll(WINS);

        List<WinEntity> wins = LiveDataTestUtil.getValue(mWinDao.loadWins(WIN_ENTITY.getPlayerId()));
        assertThat(wins.size(), is (1));
    }

    @Test
    public void getWinByPlayerId() throws InterruptedException {
        mWinDao.insertAll(WINS);
        mPlayerDao.insertAll(PLAYERS);

        List<WinEntity> wins = LiveDataTestUtil.getValue(mWinDao.loadWins(WIN_ENTITY.getPlayerId()));
        assertThat(wins.size(), is(1));
    }
}
