package com.example.tournamentbracketcreator.adapter;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tournamentbracketcreator.fragment.BracketColumnFragment;
import com.example.tournamentbracketcreator.model.ColumnData;

import java.util.ArrayList;

public class BracketsSectionAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = "BracketsSectionAdapter";

    private ArrayList<ColumnData> sectionList;

    public BracketsSectionAdapter(FragmentManager fm, ArrayList<ColumnData> sectionList) {
        super(fm);
        this.sectionList = sectionList;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: starts");
        Bundle bundle = new Bundle();
        bundle.putSerializable("column_data", this.sectionList.get(position));
        BracketColumnFragment fragment = new BracketColumnFragment();
        bundle.putInt("section_number", position);
        if (position > 0) {
            bundle.putInt("previous_section_size", sectionList.get(position - 1)
                    .getMatches().size());
        } else if (position == 0) {
            bundle.putInt("previous_section_size", sectionList.get(position)
                    .getMatches().size());
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return this.sectionList.size();

    }
}
