package com.example.tournamentbracketcreator.view;

import androidx.lifecycle.ViewModel;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
