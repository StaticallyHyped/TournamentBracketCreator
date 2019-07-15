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
import android.widget.ImageButton;

import com.example.tournamentbracketcreator.view.PlayerpoolAViewModel;
import com.example.tournamentbracketcreator.R;

public class PlayerpoolAFragment extends Fragment {

    private PlayerpoolAViewModel mViewModel;

    public static PlayerpoolAFragment newInstance() {
        return new PlayerpoolAFragment();
    }
    public static final String TAG = "PlayerpoolAFragment";

    public Button addToList, removeFromList;
    public ImageButton addToTournPool, removeFromTournPool;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_playerpoola, container, false);

        addToList = root.findViewById(R.id.add_new_playerBtn);
        removeFromList = root.findViewById(R.id.remove_playerBtn);
        addToTournPool = root.findViewById(R.id.add_to_tournBtn);
        removeFromTournPool = root.findViewById(R.id.remove_from_tournBtn);

        return inflater.inflate(R.layout.fragment_playerpoola, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlayerpoolAViewModel.class);
        // TODO: Use the ViewModel
    }

}
