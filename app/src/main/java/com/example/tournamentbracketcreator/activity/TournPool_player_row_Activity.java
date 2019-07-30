package com.example.tournamentbracketcreator.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tournamentbracketcreator.R;

public class TournPool_player_row_Activity extends AppCompatActivity {
    public static final String TAG = "TournPool_player_row_Activity";
    private Button playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tournpool_player_row);
        playerName = findViewById(R.id.txt_name);
        playerName.setBackgroundColor(Color.parseColor("#666bff"));
    }
}
