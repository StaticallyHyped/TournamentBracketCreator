package com.example.tournamentbracketcreator.fragment;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.view.RatingsViewModel;

public class RatingsFragment extends Fragment {

    private RatingsViewModel mViewModel;

    public static RatingsFragment newInstance() {
        return new RatingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ratings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RatingsViewModel.class);
        // TODO: Use the ViewModel
    }

}
