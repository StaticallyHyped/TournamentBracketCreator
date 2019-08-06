package com.example.tournamentbracketcreator.fragment;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tournamentbracketcreator.adapter.BracketsSectionAdapter;
import com.example.tournamentbracketcreator.model.ColumnData;
import com.example.tournamentbracketcreator.model.CompetitorData;
import com.example.tournamentbracketcreator.model.MatchData;
import com.example.tournamentbracketcreator.utility.BracketsUtility;
import com.example.tournamentbracketcreator.view.BracketsViewModel;
import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.view.WrapContentHtViewPager;

import java.util.ArrayList;
import java.util.List;

public class BracketsFragment extends Fragment implements ViewPager.OnPageChangeListener{
    public static final String TAG = "BracketsFragment";

    private BracketsViewModel mViewModel;
    private WrapContentHtViewPager viewPager;
    private BracketsSectionAdapter sectionAdapter;
    private ArrayList<ColumnData> sectionList;
    private int mNextSelectedScreen;
    private int mCurrentPagerState;

    public static BracketsFragment newInstance() {
        return new BracketsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");
        
        return inflater.inflate(R.layout.fragment_brackets, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BracketsViewModel.class);
        // TODO: Use the ViewModel
        initViews();
        setData();
        initViewPagerAdapter();
    }

    private void setData(){
        Log.d(TAG, "setData: starts");
        sectionList = new ArrayList<>();
        ArrayList<MatchData> column1MatchesList = new ArrayList<>();
        ArrayList<MatchData> column2MatchesList = new ArrayList<>();
        ArrayList<MatchData> column3MatchesList = new ArrayList<>();
        CompetitorData competitorOne = new CompetitorData("Name 1", "0");
        CompetitorData competitorTwo = new CompetitorData("Name 2", "0");
        CompetitorData competitorThree = new CompetitorData("Name 3", "3");
        CompetitorData competitorFour = new CompetitorData("Name 4", "4");
        CompetitorData competitorFive = new CompetitorData("Name 5", "1");
        CompetitorData competitorSix = new CompetitorData("Name 6", "1");
        CompetitorData competitorSeven = new CompetitorData("Name 7", "1");
        CompetitorData competitorEight = new CompetitorData("Name 8", "1");
//      randomly select competitors to be comp1, comp2, comp3, etc
        MatchData matchData1 = new MatchData(competitorOne, competitorTwo);
        MatchData matchData2 = new MatchData(competitorThree, competitorFour);
        MatchData matchData3 = new MatchData(competitorFive, competitorSix);
        MatchData matchData4 = new MatchData(competitorSeven, competitorEight);
        //add to first column
        column1MatchesList.add(matchData1);
        column1MatchesList.add(matchData2);
        column1MatchesList.add(matchData3);
        column1MatchesList.add(matchData4);
        ColumnData column1Data = new ColumnData(column1MatchesList);
        sectionList.add(column1Data);
        //END COLUMN 1 DATA, START COLUMN TWO - to be generated programmatically based on wins
        CompetitorData competitorNine = new CompetitorData("Joe Rogers", "8");
        CompetitorData competitorTen = new CompetitorData("Chelsea Clinton", "11");
        CompetitorData competitorEleven = new CompetitorData("Oscar Wilde", "14");
        CompetitorData competitorTwelve = new CompetitorData("Spindly Pete", "12");
        MatchData matchData5 = new MatchData(competitorNine,competitorTen);
        MatchData matchData6 = new MatchData(competitorEleven, competitorTwelve);
        column2MatchesList.add(matchData5);
        column2MatchesList.add(matchData6);
        ColumnData columnData2 = new ColumnData(column2MatchesList);
        sectionList.add(columnData2);
        CompetitorData competitorThirteen = new CompetitorData("Chelsea Clinton", "TBD");
        CompetitorData competitorFourteen = new CompetitorData("Oscar Wilde", "TBD");
        MatchData matchData7 = new MatchData(competitorThirteen, competitorFourteen);
        column3MatchesList.add(matchData7);
        //END COLUMN 2 DATA, START COLUMN 3
        ColumnData columnData3 = new ColumnData(column3MatchesList);
        sectionList.add(columnData3);

    }

    private void initViewPagerAdapter(){
        Log.d(TAG, "initViewPagerAdapter: starts");
        sectionAdapter = new BracketsSectionAdapter(getChildFragmentManager(), this.sectionList);
        viewPager.setOffscreenPageLimit(10);
        viewPager.setAdapter(sectionAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageMargin(-200);
        viewPager.setHorizontalFadingEdgeEnabled(true);
        viewPager.setFadingEdgeLength(50);
        viewPager.addOnPageChangeListener(this);
    }

    private void initViews(){
        Log.d(TAG, "initViews: starts");
        viewPager = (WrapContentHtViewPager) getView().findViewById(R.id.frag_brackets_container);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
        if (mCurrentPagerState != ViewPager.SCROLL_STATE_SETTLING){
            if (positionOffset > .5){
                if (position + 1 != mNextSelectedScreen){
                    mNextSelectedScreen = position + 1;
                    if (getBracketsFragment(position).getColumnList().get(0).getHeight()
                    != BracketsUtility.dpToPx(131))
                        getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                    if (getBracketsFragment(position + 1).getColumnList().get(0).getHeight()
                    != BracketsUtility.dpToPx(131))getBracketsFragment(position + 1).shrinkView(BracketsUtility.dpToPx(131));
                }
            } else {
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    if (getBracketsFragment(position + 1).getCurrentBracketSize() ==
                    getBracketsFragment(position + 1).getPreviousBracketSize()){
                        getBracketsFragment(position + 1).shrinkView(BracketsUtility.dpToPx(131));
                        getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                    } else {
                        int currentFragmentSize = getBracketsFragment(position + 1).getCurrentBracketSize();
                        int previousBracketSize = getBracketsFragment(position + 1).getPreviousBracketSize();
                        if (currentFragmentSize != previousBracketSize){
                            getBracketsFragment(position + 1).expandHeight(BracketsUtility.dpToPx(262));
                            getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                        }
                    }
                }
            }
        }else {
            if (positionOffset > 0.5) {
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                }
            } else {
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public BracketColumnFragment getBracketsFragment(int position){
        BracketColumnFragment bracketsFrag = null;
        if (getChildFragmentManager() != null){
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof BracketColumnFragment){
                        bracketsFrag = (BracketColumnFragment) fragment;
                        if (bracketsFrag.getSectionNumber() == position)
                            break;
                    }
                }
            }
        }
        return bracketsFrag;
    }

}
