package com.example.tournamentbracketcreator.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.amplify.generated.graphql.ListTtPlayersQuery;
import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.model.Data;

import java.util.ArrayList;
import java.util.List;

public class TournPoolRVAdapter extends RecyclerView.Adapter<TournPoolRVAdapter.ViewHolder> {

    public static final String TAG = "TournPoolRVAdapter";

    private List<ListTtPlayersQuery.Item> playersData = new ArrayList<>();
    private LayoutInflater mInflater;
    private enum hasSelected {YES, NO}
    private hasSelected mMode;
    //private ArrayList<String> tournPool = new ArrayList<>();
    public static ArrayList<String[]> tournPool;

    //constructor from dev.to/kkemple. May need to include Context as parameter?
    public TournPoolRVAdapter(Context context){
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.tournpool_player_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: starts");
        ListTtPlayersQuery.Item item = playersData.get(position);
        holder.txt_id.setVisibility(View.INVISIBLE);
        holder.txt_name.setText(item.name());
        holder.txt_id.setText(item.id());


        //Initialize ArrayList for tournament pool data
        tournPool = Data.getTournPoolList();

        mMode = hasSelected.NO;
        holder.playerContainer.setOnClickListener(v ->
        {
            String[] player = new String[2];
            Log.d(TAG, "onBindViewHolder: clicked the container" + holder.playerContainer.getId());
            Log.d(TAG, "onBindViewHolder: clicked the button" + holder.txt_name.getId());
            switch (mMode){
                case NO:
                    player[0] = holder.txt_name.getText().toString();
                    player[1] = holder.txt_id.getText().toString();
                    holder.playerContainer.setBackgroundColor(Color.LTGRAY);
                    tournPool.add(player);
                    //tournPool.add(holder.txt_name.getText().toString());
                    mMode = hasSelected.YES;
                    break;
                case YES:
                    holder.playerContainer.setBackgroundColor(Color.WHITE);
                    /*String[] player = {holder.txt_name.getText().toString(),
                            holder.txt_id.getText().toString()};*/
                    tournPool.remove(player);
                    mMode = hasSelected.NO;
                    break;
                    default:
                        holder.playerContainer.setBackgroundColor(Color.WHITE);
            }
            //Data.setTournPoolList(tournPool);
            Log.d(TAG, "onBindViewHolder: " + tournPool.toString());

        });
        holder.txt_name.setOnClickListener(v -> {
            Log.d(TAG, "onBindViewHolder: button id: " + holder.txt_name.getId());
            String[] player = new String[2];
            switch (mMode){
                case NO:
                    player[0] = holder.txt_name.getText().toString();
                    player[1] = holder.txt_id.getText().toString();
                    holder.playerContainer.setBackgroundColor(Color.LTGRAY);
                    tournPool.add(player);
                    //Data.setTournPoolList(tournPool);
                    mMode = hasSelected.YES;
                    break;
                case YES:
                    holder.playerContainer.setBackgroundColor(Color.WHITE);
                    tournPool.remove(player);
                    mMode = hasSelected.NO;
                    break;
                default:
                    holder.playerContainer.setBackgroundColor(Color.WHITE);
            }
        });
        Log.d(TAG, "onBindViewHolder: getTournPoolList: " + Data.getTournPoolList().toString());
        Data.setTournPoolList(tournPool);
        //holder.bindData(playersData.get(position));
    }
    //from dev.to/kkemple example
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + playersData.size());
        return playersData.size();

    }
    public void setItems(List<ListTtPlayersQuery.Item> items){
        playersData = items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Button txt_name;
        private TextView txt_id;
        //ConstraintLayout poolACL;
        LinearLayout playerContainer;

        ViewHolder(View itemView){
            super(itemView);
            Log.d(TAG, "ViewHolder: starts in itemView");
            this.txt_name = itemView.findViewById(R.id.txt_name);
            this.txt_id = itemView.findViewById(R.id.text_id);
            this.playerContainer = itemView.findViewById(R.id.layout_playeritem);
            //txt_description= itemView.findViewById(R.id.txt_description);
        }
        /*void bindData(ListTtPlayersQuery.Item item){
            txt_name.setText(item.name());
            // Only need  player name?
            //txt_description.setText(item.description);
        }*/
    }

}
