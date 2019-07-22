package com.example.tournamentbracketcreator.model;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private static final String TAG = "MySQLConnector";


    public static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String db = "U04n6X";
    private static final String url = "jdbc:mysql://52.206.157.109/ " + db;
    private static final String user = "U04n6X";
    private static final String pass = "53688291768";

    public static void makeConnection() throws Exception{
        try{//may need Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            Log.d(TAG, "makeConnection: connected to db: " + db);
        } catch (SQLException e){
            Log.d(TAG, "makeConnection: failed to connect");
            Log.d(TAG, "makeConnection: " + e.getMessage());
            Log.d(TAG, "makeConnection: " + e.getSQLState());
            Log.d(TAG, "makeConnection: " + e.getErrorCode());
        }
    }
}
