package com.example.tournamentbracketcreator.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tournamentbracketcreator.adapter.ViewPagerAdapter;
import com.example.tournamentbracketcreator.view.PlayerpoolAViewModel;
import com.example.tournamentbracketcreator.R;

public class PlayerpoolAFragment extends Fragment {

    private PlayerpoolAViewModel mViewModel;
    private String title;
    private int page;
    public ViewPager viewPager;
    public ViewPagerAdapter vAdapter;

    public static PlayerpoolAFragment newInstance(int page, String title) {
        PlayerpoolAFragment poolAFrag = new PlayerpoolAFragment();
        Bundle args = new Bundle();
        args.putInt("intKey", page);
        args.putString("stringKey", title);
        poolAFrag.setArguments(args);
        return poolAFrag;
    }
    public static final String TAG = "PlayerpoolAFragment";

    public Button addToList, removeFromList;
    public ImageButton addToTournPool, removeFromTournPool;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("intKey", 0);
       // title = getArguments().getString("stringKey");

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_playerpoola, container, false);

        addToList = root.findViewById(R.id.add_new_playerBtn);
        removeFromList = root.findViewById(R.id.remove_playerBtn);
        addToTournPool = root.findViewById(R.id.add_to_tournBtn);
        removeFromTournPool = root.findViewById(R.id.remove_from_tournBtn);
        viewPager = root.findViewById(R.id.tourn_pool_pager);
        vAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(vAdapter);

        //return inflater.inflate(R.layout.fragment_playerpoola, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlayerpoolAViewModel.class);
        // TODO: Use the ViewModel
    }

}
