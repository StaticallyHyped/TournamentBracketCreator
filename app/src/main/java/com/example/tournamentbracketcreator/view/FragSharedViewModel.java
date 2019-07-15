package com.example.tournamentbracketcreator.view;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.StartnavFragment;

public class FragSharedViewModel extends ViewModel {

    public static final String TAG = "FragSharedViewModel";



    public void openNewFrag (View v, Button button, FragmentTransaction fragTran,
                             int container, Fragment fragment){

        Log.d(TAG, "openNewFrag: starts");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // view is the root
                v.setVisibility(View.INVISIBLE);
            }
        });
        Log.d(TAG, "openStartnavFragment: exiting");

    }
}
