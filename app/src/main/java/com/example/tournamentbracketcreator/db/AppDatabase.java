package com.example.tournamentbracketcreator.db;

import android.content.Context;
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
import com.example.tournamentbracketcreator.db.AppExecutors;
import com.example.tournamentbracketcreator.dao.PlayerDao;
import com.example.tournamentbracketcreator.dao.WinDao;
import com.example.tournamentbracketcreator.entity.CompetitorEntity;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.entity.PlayerFtsEntity;
import com.example.tournamentbracketcreator.entity.WinEntity;
import com.example.tournamentbracketcreator.utility.DateConverter;

import java.util.List;

@Database(entities = {PlayerEntity.class, PlayerFtsEntity.class, WinEntity.class, CompetitorEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String TAG = "AppDatabase";

    private static AppDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "tournament-db";

    public abstract PlayerDao playerDao();
    public abstract WinDao winDao();
    public abstract CompetitorDao competitorDao();


    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            List<PlayerEntity> players = DataGenerator.generatePlayers();
                            List<WinEntity> wins = DataGenerator.generateWinsForPlayers(players);
                            List<CompetitorEntity> competitors = DataGenerator.generateCompetitors();
                            /*List<CommentEntity> comments =
                                    DataGenerator.generateCommentsForProducts(products);*/

                            //insertData(database, products, comments);
                            insertData(database, players, wins, competitors);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                })
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    /*private static void insertData(final AppDatabase database, final List<ProductEntity> products,
                                   final List<CommentEntity> comments)*/
    private static void insertData(final AppDatabase database, final List<PlayerEntity> players,
                                   final List<WinEntity> wins, final List<CompetitorEntity> competitors){
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
        return mIsDatabaseCreated;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `playersFts` USING FTS4("
                    + "`name` TEXT, content=`players`)");
            database.execSQL("INSERT INTO playersFts (`rowid`, `name`) "
                    + "SELECT `id`, `name` FROM players");
        }
    };
}

