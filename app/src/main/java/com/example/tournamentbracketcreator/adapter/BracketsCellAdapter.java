package com.example.tournamentbracketcreator.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.db.AppDatabase;
import com.example.tournamentbracketcreator.entity.MatchEntity;
import com.example.tournamentbracketcreator.fragment.BracketColumnFragment;
import com.example.tournamentbracketcreator.fragment.BracketsFragment;
import com.example.tournamentbracketcreator.model.MatchData;
import com.example.tournamentbracketcreator.ui.ArrowClickCallback;
import com.example.tournamentbracketcreator.viewholder.BracketsCellViewHolder;

import java.util.ArrayList;
import java.util.List;


public class BracketsCellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "BracketsCellAdapter";

    private BracketColumnFragment fragment;
    private Context context;
    private ArrayList<MatchData> playerData;
    public LiveData<List<MatchEntity>> matchList;
    public ArrayList<MatchData> updateMatch;
    public boolean handler;
    AppDatabase mDb;
    public ArrowClickCallback mListener;
    public ImageButton arrowButton;
    public RelativeLayout cellViewLayout;

    public BracketsFragment bracketsFragment = new BracketsFragment();

    public BracketsCellAdapter(BracketColumnFragment bracketColumnFragment, Context context,
        ArrayList<MatchData> playerData, ArrowClickCallback listener){
        this.fragment = bracketColumnFragment;
        this.context = context;
        this.playerData = playerData;
       // mArrowClickCallback = callback;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_cellbracket, parent, false);
        return new BracketsCellViewHolder(view, mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: starts");
        BracketsCellViewHolder viewHolder = null;

        if (holder instanceof BracketsCellViewHolder){
            viewHolder = (BracketsCellViewHolder) holder;
           // arrowButton = viewHolder.updateScoreButton;
            cellViewLayout = viewHolder.root;
            setFields(viewHolder, position);
            BracketsCellViewHolder finalViewHolder = viewHolder;
            arrowButton = viewHolder.updateScoreButton;
            arrowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: clicked");
                    String teamOneScore = finalViewHolder.getTeamOneScore().getText().toString();
                    String teamTwoScore = finalViewHolder.getTeamTwoScore().getText().toString();

                }
            });
            /*BracketsCellViewHolder finalViewHolder = viewHolder;
            String teamOneScore = finalViewHolder.getTeamOneScore().getText().toString();
            String teamOneName = finalViewHolder.getTeamOneName().getText().toString();*/

        }
    }

    private void setFields(final BracketsCellViewHolder viewHolder, final int position){
        Log.d(TAG, "setFields: starts");
        handler = new Handler().postDelayed((Runnable) () -> {
            viewHolder.setAnimation(playerData.get(position).getHeight());
        },  100);
        viewHolder.getTeamOneName().setText(playerData.get(position)
                .getCompetitorOne().getName());
        viewHolder.getTeamTwoName().setText(playerData.get(position)
                .getCompetitorTwo().getName());

    }

    @Override
    public int getItemCount() {
        return this.playerData.size();
    }

    public void setPlayerData(ArrayList<MatchData> columnList) {
        Log.d(TAG, "setPlayerData: starts");
        this.playerData = columnList;
        notifyDataSetChanged();
    }
}
