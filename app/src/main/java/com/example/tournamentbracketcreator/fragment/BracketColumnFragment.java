package com.example.tournamentbracketcreator.fragment;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tournamentbracketcreator.view.BracketColumnViewModel;
import com.example.tournamentbracketcreator.R;

public class BracketColumnFragment extends Fragment {

    private BracketColumnViewModel mViewModel;

    public static BracketColumnFragment newInstance() {
        return new BracketColumnFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bracket_columns, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BracketColumnViewModel.class);
        // TODO: Use the ViewModel
    }

}
