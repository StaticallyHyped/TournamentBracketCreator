package com.example.tournamentbracketcreator.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.adapter.ViewPagerAdapter;
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
    View fragPoolContainer;
    public ViewPager viewPager;
    public ViewPagerAdapter vAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");
        View root = inflater.inflate(R.layout.fragment_startnav, container, false);

        mViewModel = ViewModelProviders.of(this).get(StartnavViewModel.class);
        startTourn = root.findViewById(R.id.fragment_startnav_startTournBtn);
        viewWL = root.findViewById(R.id.fragment_startnav_resultsBtn);
        viewRatings = root.findViewById(R.id.fragment_startnav_ratingsBtn);
        /*viewPager = getActivity().findViewById(R.id.tourn_pool_pager);
        vAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(vAdapter);*/
        //openPlayerPoolAFragment(root);

        startTournActivity(root, startTourn, playerpoolAFragment);
        openNewFrag(root, viewWL, wlRecordsFragment);
        openNewFrag(root, viewRatings, ratingsFragment);
        //startTournActivity(startTourn);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        /*viewPager = getActivity().findViewById(R.id.main_view_pager);
        //viewPager.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager()));
        viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager()));*/
        super.onActivityCreated(savedInstanceState);
        /*vAdapter = new ViewPagerAdapter(getResources(), getChildFragmentManager());

        //this seems like it's going to break stuff, but here we go anyway
        viewPager.setAdapter(vAdapter);*/


        // TODO: Use the ViewModel
    }
    /*public boolean onBackPressed() {
        // currently visible tab Fragment
        OnBackPressListener currentFragment = (OnBackPressListener) vAdapter.getRegisteredFragment(viewPager.getCurrentItem());

        if (currentFragment != null) {
            // lets see if the currentFragment or any of its childFragment can handle onBackPressed
            return currentFragment.onBackPressed();
        }

        // this Fragment couldn't handle the onBackPressed call
        return false;
    }*/



    private void openNewFrag(final View view, Button button, final Fragment fragment) {
        Log.d(TAG, "openNewFrag: ");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(View.INVISIBLE);
//                viewPager = getActivity().findViewById(R.id.main_view_pager);
//                viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager()));
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.hide(fragment);
                fragmentTransaction.replace(R.id.login_fragment_container, fragment)
                        .addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }

    private void startTournActivity(final View view, Button button, final Fragment fragment) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewPager = getActivity().findViewById(R.id.tourn_pool_pager);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.hide(fragment);
                fragmentTransaction.replace(R.id.login_fragment_container, fragment)
                        .addToBackStack("frag_player_pool");
                fragmentTransaction.commit();
            }
        });

    }

}
