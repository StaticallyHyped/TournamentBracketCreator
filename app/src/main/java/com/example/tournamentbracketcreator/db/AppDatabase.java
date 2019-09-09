package com.example.tournamentbracketcreator.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tournamentbracketcreator.dao.CompetitorDao;
import com.example.tournamentbracketcreator.dao.MatchDao;
import com.example.tournamentbracketcreator.db.AppExecutors;
import com.example.tournamentbracketcreator.dao.PlayerDao;
import com.example.tournamentbracketcreator.dao.WinDao;
import com.example.tournamentbracketcreator.entity.CompetitorEntity;
import com.example.tournamentbracketcreator.entity.MatchEntity;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.entity.PlayerFtsEntity;
import com.example.tournamentbracketcreator.entity.WinEntity;
import com.example.tournamentbracketcreator.utility.DateConverter;

import java.util.List;

@Database(entities = {PlayerEntity.class, PlayerFtsEntity.class,
        WinEntity.class, CompetitorEntity.class, MatchEntity.class}, version = 1)
//, exportSchema = false
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String TAG = "AppDatabase";
   // private static AppDatabase sInstance;
    private static AppDatabase INSTANCE;

    @VisibleForTesting
    public static final String DATABASE_NAME = "tournament-db-test1_1";

    public abstract PlayerDao playerDao();
    public abstract WinDao winDao();
    public abstract CompetitorDao competitorDao();
    public abstract MatchDao matchDao();


    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            Log.d(TAG, "getDatabase: INSTANCE == null");
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            //TODO disallow queries on Main thread
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
        }
        Log.d(TAG, "getDatabase: exiting");
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        Log.d(TAG, "updateDatabaseCreated: starts");
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            Log.d(TAG, "updateDatabaseCreated: db exists");
            setDatabaseCreated();
        }
        Log.d(TAG, "updateDatabaseCreated: db does not exist");
    }

    private void setDatabaseCreated(){
        Log.d(TAG, "setDatabaseCreated: starts");
        mIsDatabaseCreated.postValue(true);
    }

    /*private static void insertData(final AppDatabase database, final List<ProductEntity> products,
                                   final List<CommentEntity> comments)*/
    private static void insertData(final AppDatabase database, final List<PlayerEntity> players,
                                   final List<WinEntity> wins, final List<CompetitorEntity> competitors){
        Log.d(TAG, "insertData: starts");
        database.runInTransaction(() -> {
            database.playerDao().insertAll(players);
            database.winDao().insertAll(wins);
            database.competitorDao().insertAll(competitors);
        });
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        Log.d(TAG, "getDatabaseCreated: starts");
        return mIsDatabaseCreated;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            Log.d(TAG, "migrate: starts");
            database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `playersFts` USING FTS4("
                    + "`name` TEXT, content=`players`)");
            database.execSQL("INSERT INTO playersFts (`rowid`, `name`) "
                    + "SELECT `id`, `name` FROM players");
        }
    };
}

