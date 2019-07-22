package com.example.tournamentbracketcreator.model;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.tournamentbracketcreator.contracts.PlayerContract;

public class AppProvider extends ContentProvider {

    public static final String TAG = "AppProvider";
    private AppDatabase mOpenHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    public static final String CONTENT_AUTHORITY = "com.example.tournamentbracketcreator.provider";

    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final int PLAYERS = 100;
    public static final int PLAYER_ID = 101;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(CONTENT_AUTHORITY, PlayerContract.PLAYERS_TABLE_NAME, PLAYERS);
        matcher.addURI(CONTENT_AUTHORITY, PlayerContract.PLAYERS_TABLE_NAME + "/#", PLAYER_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = AppDatabase.getInstance(getContext());
        return false;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        return null;
    }


    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PLAYERS:
                return PlayerContract.CONTENT_TYPE;
            case PLAYER_ID:
                return PlayerContract.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
