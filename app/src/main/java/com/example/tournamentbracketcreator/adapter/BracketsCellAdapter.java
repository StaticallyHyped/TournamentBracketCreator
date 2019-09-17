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
import androidx.recyclerview.widget.RecyclerView;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.dao.MatchDao;
import com.example.tournamentbracketcreator.db.AppDatabase;
import com.example.tournamentbracketcreator.entity.MatchEntity;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.fragment.BracketColumnFragment;
import com.example.tournamentbracketcreator.fragment.BracketsFragment;
import com.example.tournamentbracketcreator.model.MatchData;
import com.example.tournamentbracketcreator.ui.ArrowClickCallback;
import com.example.tournamentbracketcreator.viewholder.BracketsCellViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.example.tournamentbracketcreator.fragment.BracketsFragment.staticMatchesList;
import static com.example.tournamentbracketcreator.fragment.BracketsFragment.staticPlayersList;


public class BracketsCellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "BracketsCellAdapter";

    private BracketColumnFragment fragment;
    private Context context;
    private ArrayList<MatchData> playerData;
    public static List<MatchEntity> matchList;
    public static List<PlayerEntity> playerList;
    private boolean handler;
    public ArrowClickCallback mListener;
    public ImageButton arrowButton;
    public RelativeLayout cellViewLayout;
    private AppDatabase mDb;

    public BracketsFragment bracketsFragment = new BracketsFragment();

    public BracketsCellAdapter(BracketColumnFragment bracketColumnFragment, Context context,
        ArrayList<MatchData> playerData){
        this.fragment = bracketColumnFragment;
        this.context = context;
        this.playerData = playerData;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_cellbracket, parent, false);
        matchList = staticMatchesList;
        playerList = staticPlayersList;
        mDb = AppDatabase.getDatabase(parent.getContext());
        return new BracketsCellViewHolder(view, mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: starts");
        BracketsCellViewHolder viewHolder = null;

        if (holder instanceof BracketsCellViewHolder){
            viewHolder = (BracketsCellViewHolder) holder;
            cellViewLayout = viewHolder.root;
            setFields(viewHolder, position);
//            matchList = bracketsFragment.getMatchList();
            BracketsCellViewHolder finalViewHolder = viewHolder;
            arrowButton = viewHolder.updateScoreButton;

            arrowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: clicked");
                    String teamOneScore = finalViewHolder.getTeamOneScore().getText().toString();
                    String teamTwoScore = finalViewHolder.getTeamTwoScore().getText().toString();

                    if (Integer.parseInt(teamOneScore) > Integer.parseInt(teamTwoScore)){
                        Log.d(TAG, "onClick: clicked");

//                        mDb.matchDao().replaceMatch(matchList.get(position + 2));
                        PlayerEntity playerOne = new PlayerEntity(playerData.get(position).getCompetitorOne().getId(), finalViewHolder.getTeamOneName().toString());
                        PlayerEntity playerTwo = new PlayerEntity(playerData.get(position).getCompetitorTwo().getId(), finalViewHolder.getTeamTwoName().toString());
//                        playerData.add(new MatchData(10, playerOne, playerTwo));
                        mDb.matchDao().insertMatch(new MatchEntity(playerOne.getId(), playerTwo.getId()));

                    }

                    notifyDataSetChanged();
                }
            });
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
