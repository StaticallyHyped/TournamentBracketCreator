package com.example.tournamentbracketcreator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.databinding.TournpoolPlayerRowBinding;
import com.example.tournamentbracketcreator.fragment.PlayerpoolAFragment;
import com.example.tournamentbracketcreator.model.Player;
import com.example.tournamentbracketcreator.ui.PlayerClickCallback;
import com.example.tournamentbracketcreator.viewholder.PoolAViewHolder;

import java.util.List;
import java.util.Objects;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    public static final String TAG = "PlayerAdapter";

    List<? extends Player> mPlayerList;
    Context mContext;
    private LayoutInflater mInflater;

    @javax.annotation.Nullable
    private final PlayerClickCallback mPlayerClickCallback;

   /* public PlayerAdapter(PlayerpoolAFragment context){
        this.mInflater = LayoutInflater.from(context);
    }*/
    public PlayerAdapter(@Nullable PlayerClickCallback clickCallback){
        mPlayerClickCallback = clickCallback;
        setHasStableIds(true);
    }

    public void setPlayerList(final List<? extends Player> playerList){
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
                    return mPlayerList.get(oldItemPosition).getId() ==
                            playerList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Player newPlayer = playerList.get(newItemPosition);
                    Player oldPlayer = mPlayerList.get(oldItemPosition);
                    return newPlayer.getId() == oldPlayer.getId()
                            && Objects.equals(newPlayer.getName(), oldPlayer.getName())
                            && Objects.equals(newPlayer.getId(), oldPlayer.getId());

                }
            });
            mPlayerList = playerList;
            result.dispatchUpdatesTo(this);
        }
    }


    @Override //needs to be PlayerListViewHolder
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TournpoolPlayerRowBinding binding = DataBindingUtil.inflate(LayoutInflater
                        .from(parent.getContext()),
                R.layout.tournpool_player_row, parent, false);
        binding.setCallback(mPlayerClickCallback);

        return new PlayerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.binding.setPlayer(mPlayerList.get(position));
        holder.binding.executePendingBindings();
    }

    /*@Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PoolAViewHolder viewHolder = null;
        if (holder instanceof PoolAViewHolder){
            viewHolder = (PoolAViewHolder) holder;
            viewHolder.binding.setPlayer(mPlayerList.get(position));
            viewHolder.binding.executePendingBindings();
        }
    }*/

    @Override
    public int getItemCount() {
        return mPlayerList == null ? 0 : mPlayerList.size();
    }

    @Override
    public long getItemId(int position){
        return mPlayerList.get(position).getId();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        final TournpoolPlayerRowBinding binding;

        public PlayerViewHolder(TournpoolPlayerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
