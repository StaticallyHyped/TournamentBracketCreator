package com.example.tournamentbracketcreator.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.BracketColumnFragment;
import com.example.tournamentbracketcreator.model.MatchData;
import com.example.tournamentbracketcreator.viewholder.BracketsCellViewHolder;

import java.util.ArrayList;


public class BracketsCellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BracketColumnFragment fragment;
    private Context context;
    private ArrayList<MatchData> playerData;
    private boolean handler;

    public BracketsCellAdapter(BracketColumnFragment bracketColumnFragment, Context context,
        ArrayList<MatchData> playerData){
        this.fragment = bracketColumnFragment;
        this.context = context;
        this.playerData = playerData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_cellbracket, parent, false);
        return new BracketsCellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BracketsCellViewHolder viewHolder = null;
        if (holder instanceof BracketsCellViewHolder){
            viewHolder = (BracketsCellViewHolder) holder;
            setFields(viewHolder, position);
        }
    }
    private void setFields(final BracketsCellViewHolder viewHolder, final int position){
        handler = new Handler().postDelayed((Runnable) () -> {
            viewHolder.setAnimation(playerData.get(position).getHeight());
        },  100);

        viewHolder.getTeamOneName().setText(playerData.get(position)
                .getCompetitorOne().getName());
        viewHolder.getTeamTwoName().setText(playerData.get(position)
                .getCompetitorTwo().getName());
        viewHolder.getTeamOneScore().setText(playerData.get(position)
                .getCompetitorOne().getScore());
        viewHolder.getTeamTwoScore().setText(playerData.get(position)
                .getCompetitorTwo().getScore());

    }

    @Override
    public int getItemCount() {
        return this.playerData.size();
    }

    public void setPlayerData(ArrayList<MatchData> columnList) {
        this.playerData = columnList;
        notifyDataSetChanged();
    }
}
