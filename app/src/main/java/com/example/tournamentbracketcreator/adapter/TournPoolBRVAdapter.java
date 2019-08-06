package com.example.tournamentbracketcreator.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.model.Data;

import java.util.ArrayList;
import java.util.List;

public class TournPoolBRVAdapter extends RecyclerView.Adapter<TournPoolBRVAdapter.ViewHolder> {
    public static final String TAG = "TournPoolBRVAdapter";

    //private List<String> poolList = new ArrayList<>();

    private LayoutInflater mInflater;
    public static ArrayList poolList = Data.getTournPoolList();
    ArrayAdapter<String> arrayAdapter;

    public TournPoolBRVAdapter(Context context){
        this.mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public TournPoolBRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.tournpool_player_row, parent, false);
        Log.d(TAG, "onCreateViewHolder: " + poolList.toString());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TournPoolBRVAdapter.ViewHolder holder, int position) {
        //Add your Getter here to loop through an arraylist and poolBList.get(position);
        Log.d(TAG, "onBindViewHolder: starts");
        //String testString = poolList.toString();
        String name = poolList.get(position).toString();
        holder.txt_name.setText(name);
        Log.d(TAG, "onBindViewHolder: item = " + name);


        /*arrayAdapter = new ArrayAdapter<String>(this, )
        String string = (String) poolList.get(position);
        String testString = poolList.to*/
       //String item = poolList.toString();

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: static list" + poolList.size());

        return poolList.size();
    }
    public void setItems(ArrayList<String> items){
        poolList = items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Button txt_name;

        ViewHolder(View itemView){
            super(itemView);
            this.txt_name = itemView.findViewById(R.id.txt_name);
        }
    }
}
