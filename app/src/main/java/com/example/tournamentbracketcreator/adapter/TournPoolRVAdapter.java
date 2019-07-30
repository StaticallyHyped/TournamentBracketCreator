package com.example.tournamentbracketcreator.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.amplify.generated.graphql.ListTtPlayersQuery;
import com.example.tournamentbracketcreator.R;

import java.util.ArrayList;
import java.util.List;

public class TournPoolRVAdapter extends RecyclerView.Adapter<TournPoolRVAdapter.ViewHolder> {

    public static final String TAG = "TournPoolRVAdapter";

    private List<ListTtPlayersQuery.Item> playersData = new ArrayList<>();
    private LayoutInflater mInflater;

    //constructor from dev.to/kkemple. May need to include Context as parameter?
    public TournPoolRVAdapter(Context context){
        this.mInflater = LayoutInflater.from(context);
    }

    //from app-sync app
    public void setPlayers(List<ListTtPlayersQuery.Item> players){
        this.playersData = players;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.tournpool_player_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListTtPlayersQuery.Item item = playersData.get(position);
        holder.txt_name.setText(item.name());
        //holder.bindData(playersData.get(position));
    }
    //from dev.to/kkemple example
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + playersData.size());
        return playersData.size();
        //from CurriculumTracker
        //If the list is null, or getCount == 0
        /*if ((List<ListTtPlayersQuery.Item>.)){
            return 1;
        } else {
            return playersData.getCount();
        }*/
    }
    public void setItems(List<ListTtPlayersQuery.Item> items){
        playersData = items;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        Button txt_name;
        //Only need player name?
        //TextView txt_description;

        ViewHolder(View itemView){
            super(itemView);
            this.txt_name = itemView.findViewById(R.id.txt_name);
            //txt_description= itemView.findViewById(R.id.txt_description);
        }
        /*void bindData(ListTtPlayersQuery.Item item){
            txt_name.setText(item.name());
            // Only need  player name?
            //txt_description.setText(item.description);
        }*/
    }

}
