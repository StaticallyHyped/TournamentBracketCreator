package com.example.tournamentbracketcreator.fragment;

//import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.TableInfo;

import android.service.autofill.FieldClassification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tournamentbracketcreator.adapter.BracketsCellAdapter;
import com.example.tournamentbracketcreator.model.ColumnData;
import com.example.tournamentbracketcreator.model.CompetitorData;
import com.example.tournamentbracketcreator.model.MatchData;
import com.example.tournamentbracketcreator.ui.ArrowClickCallback;
import com.example.tournamentbracketcreator.utility.BracketsUtility;
import com.example.tournamentbracketcreator.view.BracketColumnViewModel;
import com.example.tournamentbracketcreator.R;

import java.util.ArrayList;

public class BracketColumnFragment extends Fragment {
    public static final String TAG = "BracketColumnFragment";

    private BracketColumnViewModel mViewModel;
    private ColumnData columnData;
    private int sectionNumber = 0;
    private int previousBracketSize;
    private ArrayList<MatchData> playerData;
    private RecyclerView mBracketRV;
    private BracketsCellAdapter adapter;

    public static BracketColumnFragment newInstance() {
        return new BracketColumnFragment();
    }
    /*public BracketColumnFragment(){
        Log.d(TAG, "BracketColumnFragment: starts");
    }
*/
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bracket_columns, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: starts");
        initViews();
        getExtras();
        initAdapter();
    }
    public void initViews(){
        mBracketRV = getView().findViewById(R.id.bracket_col_pairingRV);

    }


    public ArrayList<MatchData> getColumnList(){
        return playerData;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     //   mViewModel = ViewModelProviders.of(this).get(BracketColumnViewModel.class);
        // TODO: Use the ViewModel
    }

    private void getExtras(){
        if (getArguments() != null){
            Log.d(TAG, "getExtras: != null");
            playerData = new ArrayList<>();
            columnData = (ColumnData) getArguments().getSerializable("column_data");
            sectionNumber = getArguments().getInt("section_number");
            previousBracketSize = getArguments().getInt("previous_section_size");
            Log.d(TAG, "getExtras: columnData size: " + columnData.getMatches().size());
            playerData.addAll(columnData.getMatches());
            setInitialHeightForPlayerData();
        }
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    private void setInitialHeightForPlayerData(){
        for (MatchData data : playerData){
            if (sectionNumber == 0){
                data.setHeight(BracketsUtility.dpToPx(131));
            } else if (sectionNumber == 1 && previousBracketSize != playerData.size()){
                data.setHeight(BracketsUtility.dpToPx(262));
            } else if (sectionNumber == 1 && previousBracketSize == playerData.size()){
                data.setHeight(BracketsUtility.dpToPx(131));
            } else if (previousBracketSize > playerData.size()) {
                data.setHeight(BracketsUtility.dpToPx(262));
            }else if (previousBracketSize == playerData.size()) {
                data.setHeight(BracketsUtility.dpToPx(131));
            }
        }
    }

    public void expandHeight(int height){
        Log.d(TAG, "expandHeight: starts");
        for (MatchData data : playerData){
            data.setHeight(height);
        }
        adapter.setPlayerData(playerData);
    }

    public void shrinkView(int height){
        Log.d(TAG, "shrinkView: starts");
        for (MatchData data : playerData){
            data.setHeight(height);
        }
        adapter.setPlayerData(playerData);
    }
    @SuppressLint("WrongConstant")
    public void initAdapter() {
        Log.d(TAG, "initAdapter: starts");
        adapter = new BracketsCellAdapter(this, getContext(), playerData);
        if (mBracketRV != null){
            mBracketRV.setHasFixedSize(true);
            mBracketRV.setNestedScrollingEnabled(false);
            mBracketRV.setAdapter(adapter);
            mBracketRV.smoothScrollToPosition(0);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mBracketRV.setLayoutManager(layoutManager);
            mBracketRV.setItemAnimator(new DefaultItemAnimator());
            adapter.notifyDataSetChanged();
        }
    }
    public int getCurrentBracketSize(){
        Log.d(TAG, "getCurrentBracketSize: " + playerData.size());
        return playerData.size();
    }
    public int getPreviousBracketSize(){
        return previousBracketSize;
    }

    /*@Override
    public void onArrowClick(CompetitorData competitorData) {
        Log.d(TAG, "onArrowClick: clicked");
        ArrowClickCallback listener = (BracketsCellAdapter
                .ArrowClickCallback) getTargetFragment();
        if (listener != null){
            Log.d(TAG, "onArrowClick: listener not null");
            listener.onArrowClick(competitorData);
        }

        adapter.notifyDataSetChanged();
    }*/
}
