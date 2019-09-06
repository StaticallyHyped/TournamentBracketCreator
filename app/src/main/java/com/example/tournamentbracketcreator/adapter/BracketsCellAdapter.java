package com.example.tournamentbracketcreator.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.BracketColumnFragment;
import com.example.tournamentbracketcreator.fragment.BracketsFragment;
import com.example.tournamentbracketcreator.model.CompetitorData;
import com.example.tournamentbracketcreator.model.MatchData;
import com.example.tournamentbracketcreator.model.Player;
import com.example.tournamentbracketcreator.model.PlayerData;
import com.example.tournamentbracketcreator.ui.ArrowClickCallback;
import com.example.tournamentbracketcreator.ui.PlayerClickCallback;
import com.example.tournamentbracketcreator.viewholder.BracketsCellViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BracketsCellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "BracketsCellAdapter";

    private BracketColumnFragment fragment;
    private Context context;
    private ArrayList<MatchData> playerData;
    List<? extends MatchData> mPlayerList;
    private ArrayList<MatchData> updateMatch;
    private boolean handler;
//    public static CompetitorData competitor = null;
//    public OnUpdateMatchClickListener mListener;
    public BracketsFragment bracketsFragment = new BracketsFragment();

    /*public interface OnUpdateMatchClickListener{
        void onArrowClick(CompetitorData competitorData);
    }*/


    /*@Nullable
    private final ArrowClickCallback mArrowClickCallback;*/

    public BracketsCellAdapter(BracketColumnFragment bracketColumnFragment, Context context,
        ArrayList<MatchData> playerData){
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
        return new BracketsCellViewHolder(view);
    }

    public void setPlayerMatches(final List<? extends MatchData> playerList){
        if (mPlayerList == null){
            mPlayerList = playerList;
            notifyItemRangeInserted(0, playerList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mPlayerList.size();
                }

                @Override
                public int getNewListSize() {
                    return playerList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mPlayerList.get(oldItemPosition).getId() == playerList
                            .get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    MatchData newMatch = playerList.get(newItemPosition);
                    MatchData oldMatch = mPlayerList.get(oldItemPosition);
                    return newMatch.getId() == oldMatch.getId()
                            && Objects.equals(newMatch.getCompetitorOne(), oldMatch.getCompetitorOne());

                }
            });
            mPlayerList = playerList;
            result.dispatchUpdatesTo(this);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: starts");
        BracketsCellViewHolder viewHolder = null;
        if (holder instanceof BracketsCellViewHolder){
            viewHolder = (BracketsCellViewHolder) holder;
            setFields(viewHolder, position);
            BracketsCellViewHolder finalViewHolder = viewHolder;
            String teamOneScore = finalViewHolder.getTeamOneScore().getText().toString();
            String teamOneName = finalViewHolder.getTeamOneName().getText().toString();

            //String teamTwoScore = finalViewHolder.getTeamTwoScore().getText().toString();
            //Used in the onClick method
//            final CompetitorData competitor = new CompetitorData(10, teamOneName,
//                    teamOneScore);

            View.OnClickListener buttonListener = new View.OnClickListener() {
               
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: Arrow clicked");
                    /*if (mArrowClickCallback != null){
                        Log.d(TAG, "onClick: not null");
//                        mArrowClickCallback.onArrowClick(v);
                    }*/
                    Log.d(TAG, "onClick: added competitor");
//                    CompetitorData.winnersRoundTwoData.add(competitor);
//                    bracketsFragment.getPlayer(competitor);

                }
            };
            viewHolder.updateScoreButton.setOnClickListener(buttonListener);
           /*
                    // Get the winner and loser of each match, including name and score
                    // Update each of the player's records in a mutate/cache
                    // Create a new CompetitorData object for each column, player
                    // Put the winning player into a MatchData object, and the losing player
                    // into a losing player MatchData object - must be one at a time. Set the new object's second
                    // CompetitorData object to a null, or TBD
                    // Must pass data to BracketsFragment (or BracketColumnFragment) with on-click
            */
        }
    }
    /*public static PlayerData sendMatchData(){
        return player;
    }*/

    private void setFields(final BracketsCellViewHolder viewHolder, final int position){
        Log.d(TAG, "setFields: starts");
        handler = new Handler().postDelayed((Runnable) () -> {
            viewHolder.setAnimation(playerData.get(position).getHeight());
        },  100);
        viewHolder.getTeamOneName().setText(playerData.get(position)
                .getCompetitorOne().getName());
        viewHolder.getTeamTwoName().setText(playerData.get(position)
                .getCompetitorTwo().getName());
       /* viewHolder.getTeamOneScore().setText(playerData.get(position)
                .getCompetitorOne().getScore());*/
        viewHolder.getTeamOneScore().setText("0");
        /*viewHolder.getTeamTwoScore().setText(playerData.get(position)
                .getCompetitorTwo().getScore());*/
        viewHolder.getTeamTwoScore().setText("0");
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
