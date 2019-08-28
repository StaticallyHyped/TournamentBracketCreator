package com.example.tournamentbracketcreator.db;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import com.example.tournamentbracketcreator.LiveDataTestUtil;
import com.example.tournamentbracketcreator.dao.PlayerDao;
import com.example.tournamentbracketcreator.entity.PlayerEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.example.tournamentbracketcreator.db.TestData.PLAYERS;
import static com.example.tournamentbracketcreator.db.TestData.PLAYER_ENTITY;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class PlayerDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDatabase mDatabase;

    private PlayerDao mPlayerDao;

    @Before
    public void initDb() throws Exception {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        mDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                AppDatabase.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();

        mPlayerDao = mDatabase.playerDao();
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    @Test
    public void getPlayersWhenNoPlayerInserted() throws InterruptedException {
        List<PlayerEntity> players = LiveDataTestUtil.getValue(mPlayerDao.loadAllPlayers());

        assertTrue(players.isEmpty());
    }

    @Test
    public void getPlayersAfterInserted() throws InterruptedException {
        mPlayerDao.insertAll(PLAYERS);

        List<PlayerEntity> players = LiveDataTestUtil.getValue(mPlayerDao.loadAllPlayers());

        assertThat(players.size(), is(PLAYERS.size()));
    }

    @Test
    public void getPlayerById() throws InterruptedException {
        mPlayerDao.insertAll(PLAYERS);

        PlayerEntity player = LiveDataTestUtil.getValue(mPlayerDao.loadPlayer
                (PLAYER_ENTITY.getId()));

        assertThat(player.getId(), is(PLAYER_ENTITY.getId()));
        assertThat(player.getName(), is(PLAYER_ENTITY.getName()));

    }
}
