package com.example.tournamentbracketcreator.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.view.FragSharedViewModel;
import com.example.tournamentbracketcreator.view.StartnavViewModel;

public class StartnavFragment extends Fragment {

    public Button startTourn, viewWL, viewRatings;
    public StartnavViewModel mViewModel;
    public static final String TAG = "StartnavFragment";


    public static StartnavFragment newInstance() {
        return new StartnavFragment();
    }
    PlayerpoolAFragment playerpoolAFragment = new PlayerpoolAFragment();
    WLRecordsFragment wlRecordsFragment = new WLRecordsFragment();
    RatingsFragment ratingsFragment = new RatingsFragment();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");
        View root = inflater.inflate(R.layout.fragment_startnav, container, false);

        mViewModel = ViewModelProviders.of(this).get(StartnavViewModel.class);
        startTourn = root.findViewById(R.id.fragment_startnav_startTournBtn);
        viewWL = root.findViewById(R.id.fragment_startnav_resultsBtn);
        viewRatings = root.findViewById(R.id.fragment_startnav_ratingsBtn);
        //openPlayerPoolAFragment(root);
        openNewFrag(root, startTourn, playerpoolAFragment);
        openNewFrag(root, viewWL, wlRecordsFragment);
        openNewFrag(root, viewRatings, ratingsFragment);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    /*private void openPlayerPoolAFragment(final View view){
        Log.d(TAG, "openPlayerPoolAFragment: starts");
        startTourn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerpoolAFragment poolAFrag = new PlayerpoolAFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.login_fragment_container, poolAFrag);
                fragmentTransaction.commit();
                view.setVisibility(View.INVISIBLE);
            }
        });

    }*/
    private void openNewFrag(final View view, Button button, final Fragment fragment){
        Log.d(TAG, "openNewFrag: ");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.login_fragment_container, fragment)
                .addToBackStack(null);
                fragmentTransaction.commit();
                view.setVisibility(View.INVISIBLE);
            }
        });

    }

}
