package com.example.tournamentbracketcreator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.activity.StartTournActivity;
import com.example.tournamentbracketcreator.activity.TournamentBracketActivity;
import com.example.tournamentbracketcreator.adapter.TournPoolBRVAdapter;
import com.example.tournamentbracketcreator.model.Data;

import java.util.ArrayList;
import java.util.List;

public class PlayerpoolBFragment extends Fragment implements StartTournActivity.OnButtonClicked{

    public static final String TAG = "PlayerpoolBFragment";

    private StartTournActivity mStartTournActivity;

    private String title;
    private int page;
    private Button startTournBtn;
    private RecyclerView mRecyclerViewB;
    TournPoolBRVAdapter mAdapterB;
    private List<String> poolBData;


    public static PlayerpoolBFragment newInstance(int page, String title) {
        PlayerpoolBFragment poolBFrag = new PlayerpoolBFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        poolBFrag.setArguments(args);
        return poolBFrag;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");
        View root = inflater.inflate(R.layout.fragment_playerpoolb, container, false);
        startTournBtn = root.findViewById(R.id.start_tournBtn);
        mRecyclerViewB = root.findViewById(R.id.poolb_RV);
        mRecyclerViewB.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapterB = new TournPoolBRVAdapter(getContext());

        mRecyclerViewB.setAdapter(mAdapterB);
        mRecyclerViewB.getAdapter().notifyDataSetChanged();
        mAdapterB.notifyDataSetChanged();
        mAdapterB.hasObservers();
        String myTag = getTag();


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //if doesn't work, try suppressing here and in StartTourn/FragPoolA

        mStartTournActivity = (StartTournActivity) getActivity();
        mStartTournActivity.setOnButtonClicked(this);
        super.onActivityCreated(savedInstanceState);
        startBracketActivity();
    }

    @Override
    public void submit(final String s) {
        Log.d(TAG, "submit: pressed, " + s);
        poolBData = new ArrayList<>();
        //poolBData.add(s);
        //mAdapterB.setItems(poolBData);
        mAdapterB.setItems(Data.getTournPoolList());
        mAdapterB.notifyDataSetChanged();

    }

    private void startBracketActivity(){
        startTournBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), TournamentBracketActivity.class);
            startActivity(intent);
        });
    }
    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        *//*page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");*//*
        mViewModel = ViewModelProviders.of(this).get(PlayerpoolBFragmentViewModel.class);
        // TODO: Use the ViewModel
    }*/

}
