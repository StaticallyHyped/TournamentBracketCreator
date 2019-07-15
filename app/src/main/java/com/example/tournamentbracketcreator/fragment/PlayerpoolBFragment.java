package com.example.tournamentbracketcreator.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tournamentbracketcreator.view.PlayerpoolBFragmentViewModel;
import com.example.tournamentbracketcreator.R;

public class PlayerpoolBFragment extends Fragment {

    private PlayerpoolBFragmentViewModel mViewModel;

    public static PlayerpoolBFragment newInstance() {
        return new PlayerpoolBFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playerpoolb, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlayerpoolBFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}
