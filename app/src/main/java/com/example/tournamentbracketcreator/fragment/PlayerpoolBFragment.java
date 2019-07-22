package com.example.tournamentbracketcreator.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tournamentbracketcreator.view.PlayerpoolBFragmentViewModel;
import com.example.tournamentbracketcreator.R;

public class PlayerpoolBFragment extends Fragment {

    private PlayerpoolBFragmentViewModel mViewModel;
    private String title;
    private int page;
    private Button startTournBtn;


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
        View root = inflater.inflate(R.layout.fragment_playerpoolb, container, false);
        startTournBtn = root.findViewById(R.id.start_tournBtn);
        return root;
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
