package com.example.tournamentbracketcreator.fragment;

//import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.view.WLrecordsViewModel;

public class WLRecordsFragment extends Fragment {

    private WLrecordsViewModel mViewModel;

    public static WLRecordsFragment newInstance() {
        return new WLRecordsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wlrecords, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // mViewModel = ViewModelProviders.of(this).get(WLrecordsViewModel.class);
        // TODO: Use the ViewModel
    }

}
