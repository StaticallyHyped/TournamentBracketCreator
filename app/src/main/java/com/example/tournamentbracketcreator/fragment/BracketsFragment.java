package com.example.tournamentbracketcreator.fragment;

//import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.amazonaws.amplify.generated.graphql.GetTtPlayerQuery;
import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.adapter.BracketsSectionAdapter;
import com.example.tournamentbracketcreator.db.AppDatabase;
import com.example.tournamentbracketcreator.entity.MatchEntity;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.model.ColumnData;
import com.example.tournamentbracketcreator.model.CompetitorData;
import com.example.tournamentbracketcreator.model.MatchData;
import com.example.tournamentbracketcreator.utility.BracketsUtility;
import com.example.tournamentbracketcreator.view.PlayerViewModel;
import com.example.tournamentbracketcreator.view.WrapContentHtViewPager;

import java.util.ArrayList;
import java.util.List;

public class BracketsFragment extends Fragment implements ViewPager.OnPageChangeListener{
    public static final String TAG = "BracketsFragment";

    private WrapContentHtViewPager viewPager;
    private static BracketsSectionAdapter sectionAdapter;
    private static ArrayList<ColumnData> sectionList;
    private ArrayList<ColumnData> playerSection;
    private int mNextSelectedScreen;
    private int mCurrentPagerState;
    public CompetitorData match;
    private PlayerViewModel mPlayerViewModel;
    private AppDatabase mDb;
    List<PlayerEntity> playerList;
//    public ObservableList<MatchEntity> matchList;
    public List<MatchEntity> matchList;
    public static List<MatchEntity> staticMatchesList;
    public static List<PlayerEntity> staticPlayersList;
    ArrayList<MatchData> column1MatchesList = new ArrayList<>();
    ArrayList<MatchData> column2MatchesList = new ArrayList<>();
    ArrayList<MatchData> column3MatchesList = new ArrayList<>();


    public static BracketsFragment newInstance() {
        return new BracketsFragment();
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: starts");
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");
        mPlayerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        subscribeUiPlayers();
        subscribeUiMatches();
        return inflater.inflate(R.layout.fragment_brackets, container, false);
    }


    /*public void initPlayers(List<PlayerEntity> playerEntities){
        Log.d(TAG, "initPlayers: starts");
        setPlayerData(playerEntities);
    }*/

    private void subscribeUiPlayers(){
        Log.d(TAG, "subscribeUiPlayers: starts");
        mPlayerViewModel.mObservablePlayers.observe(this, new Observer<List<PlayerEntity>>() {
            @Override
            public void onChanged(List<PlayerEntity> playerEntities) {
                Log.d(TAG, "subscribe players onChanged: starts");
                staticPlayersList = mPlayerViewModel.mObservablePlayers.getValue();
                setData(playerEntities);
                initViews();
                initViewPagerAdapter();
               // showPlayersInUi(playerEntities);
            }
        });
    }
    private void showPlayersInUi(final @NonNull List<PlayerEntity> playerEntities){
        Log.d(TAG, "showPlayersInUi: starts");
        Log.d(TAG, "showPlayersInUi: playerEntities size: " + playerEntities.size());
        /*StringBuilder sb = new StringBuilder();
        for (PlayerEntity player : playerEntities){
           sb.append(player.name);
           sb.append(player.id);
        }*/
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: starts");

    }

//    MatchData matchData5;
//    ArrayList<MatchData> column2MatchesList = new ArrayList<>();
    ColumnData columnData2 = new ColumnData(column2MatchesList);

    public void subscribeUiMatches(){
        mPlayerViewModel.mObservableMatches.observe(this, new Observer<List<MatchEntity>>() {
            @Override
            public void onChanged(List<MatchEntity> matchEntities) {
                Log.d(TAG, "subscribe matches onChanged: starts");
                staticMatchesList = mPlayerViewModel.mObservableMatches.getValue();
                setMatchData(matchEntities);
                initViews();
//                initViewPagerAdapter();
                if (staticMatchesList != null || !staticMatchesList.isEmpty()) {
                    Log.d(TAG, "onChanged: staticMatchesList: " + staticMatchesList.size());
                }
            }
        });
    }
    @Override
    public void onResume() {
        Log.d(TAG, "onResume: starts");
        super.onResume();

    }

    private void setMatchData(final @NonNull List<MatchEntity> matchEntities) {
        Log.d(TAG, "setData: starts");
        sectionList = new ArrayList<>();
        /*ArrayList<MatchData> column1MatchesList = new ArrayList<>();
        ArrayList<MatchData> column2MatchesList = new ArrayList<>();
        ArrayList<MatchData> column3MatchesList = new ArrayList<>();*/
        MatchData matchData1 = null;
        MatchData matchData2 = null;
        MatchData matchData3 = null;
        MatchData matchData4 = null;
        PlayerEntity playerSix = null;
        PlayerEntity playerEight = null;


        if (matchData1 != null) {
            Log.d(TAG, "setData: set match1");
            column1MatchesList.add(matchEntities.get(0).getId(), matchData1);
        }
        if (matchData2 != null) {
            Log.d(TAG, "setData: set match2");
            column1MatchesList.add(matchData2);
        }
        if (matchData3 != null) {
            Log.d(TAG, "setData: set match3");
            column1MatchesList.add(matchData3);
        }
        if (matchData4 != null) {
            Log.d(TAG, "setData: set match4");
            column1MatchesList.add(matchData4);
        }

        ColumnData column1Data = new ColumnData(column1MatchesList);
        sectionList.add(column1Data);
        //END COLUMN 1 DATA, START COLUMN TWO - to be generated programmatically based on wins

        PlayerEntity playerNine = new PlayerEntity("9", "TBD");
        PlayerEntity playerTen = new PlayerEntity("10","TBD");
        PlayerEntity playerEleven = new PlayerEntity("11", "TBD");
        PlayerEntity playerTwelve = new PlayerEntity("12","TBD");
        MatchData matchData5 = new MatchData(5, playerNine, playerTen);
        MatchData matchData6 = new MatchData(6, playerEleven, playerTwelve);
        column2MatchesList.add(matchData5);
        column2MatchesList.add(matchData6);
        columnData2 = new ColumnData(column2MatchesList);
        sectionList.add(columnData2);
        PlayerEntity competitorThirteen = new PlayerEntity("13","TBD");
        PlayerEntity competitorFourteen = new PlayerEntity("14","TBD");
        MatchData matchData7 = new MatchData(7, competitorThirteen, competitorFourteen);

        column3MatchesList.add(matchData7);
        //END COLUMN 2 DATA, START COLUMN 3
        ColumnData columnData3 = new ColumnData(column3MatchesList);
        sectionList.add(columnData3);

    }
   /* public void setMatchData(PlayerEntity playerOne, PlayerEntity playerTwo, PlayerEntity playerThree,
                             PlayerEntity playerFour, PlayerEntity playerFive, PlayerEntity playerSix, PlayerEntity playerSeven, PlayerEntity playerEight){

        Log.d(TAG, "setMatchData: starts");
        MatchData matchData1 = null;
        MatchData matchData2 = null;
        MatchData matchData3 = null;
        MatchData matchData4 = null;

        if (playerOne != null) {
            matchData1 = new MatchData(1, playerOne, playerTwo);
            Log.d(TAG, "setMatchData: playerOne not null");
        } else {
            matchData1 = new MatchData(1, new PlayerEntity("1", "Test Match 1"),
                    playerTwo);
        }
        if (playerFour != null) {
            Log.d(TAG, "setMatchData: playerFour not null");
            matchData2 = new MatchData(2, playerThree, playerFour);
        } else {
            matchData2 = new MatchData(2, new PlayerEntity("3", "Test Match 2"),
                    playerFour);
        }
        if (playerFive != null) {
            matchData3 = new MatchData(3, playerFive, playerSix);
        } else {
            matchData3 = new MatchData(3, new PlayerEntity("5", "Test Match 3"),
                    playerSix);
        }
        if (playerSeven != null) {
            Log.d(TAG, "setMatchData: playerSeven not null");
            matchData4 = new MatchData(4, playerSeven, playerEight);
        } else {
            matchData4 = new MatchData(3, new PlayerEntity("7", "Uh oh, something went wrong"),
                    playerEight);
        }
        setColumnData(matchData1, matchData2, matchData3, matchData4);

    }

    public void setColumnData(MatchData matchOne, MatchData matchTwo, MatchData matchThree, MatchData matchFour){
        sectionList = new ArrayList<>();
        ArrayList<MatchData> column1MatchesList = new ArrayList<>();
        ArrayList<MatchData> column2MatchesList = new ArrayList<>();
        ArrayList<MatchData> column3MatchesList = new ArrayList<>();

        if (matchOne != null) {
            Log.d(TAG, "setColumnData: matchOne not null");
            Log.d(TAG, "setColumnData: set match1");
            column1MatchesList.add(matchOne);
        }
        if (matchTwo != null) {
            Log.d(TAG, "setColumnData: set match2");
            column1MatchesList.add(matchTwo);
        }
        if (matchThree != null) {
            Log.d(TAG, "setColumnData: set match3");
            column1MatchesList.add(matchThree);
        }
        if (matchFour != null) {
            Log.d(TAG, "setColumnData: set match4");
            column1MatchesList.add(matchFour);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ColumnData column1Data = new ColumnData(column1MatchesList);
        sectionList.add(column1Data);
        //END COLUMN1, START COLUMN2

        PlayerEntity playerNine = new PlayerEntity("9", "TBD");
        Log.d(TAG, "setColumnData: competitorNine: " + playerNine.getName());
        PlayerEntity playerTen = new PlayerEntity("10","TBD");
        PlayerEntity playerEleven = new PlayerEntity("11", "TBD");
        PlayerEntity playerTwelve = new PlayerEntity("12","TBD");

        MatchData matchData5 = new MatchData(5, playerNine, playerTen);
        MatchData matchData6 = new MatchData(6, playerEleven, playerTwelve);
        column2MatchesList.add(matchData5);
        column2MatchesList.add(matchData6);
        columnData2 = new ColumnData(column2MatchesList);
        sectionList.add(columnData2);
        PlayerEntity competitorThirteen = new PlayerEntity("13","TBD");
        PlayerEntity competitorFourteen = new PlayerEntity("14","TBD");
        MatchData matchData7 = new MatchData(7, competitorThirteen, competitorFourteen);
        column3MatchesList.add(matchData7);
        //END COLUMN 2 DATA, START COLUMN 3
        ColumnData columnData3 = new ColumnData(column3MatchesList);
        sectionList.add(columnData3);

    }
*/
    public void setData(final @NonNull List<PlayerEntity> playerEntities) {
        Log.d(TAG, "setData: starts");
        sectionList = new ArrayList<>();
        ArrayList<MatchData> column1MatchesList = new ArrayList<>();
        ArrayList<MatchData> column2MatchesList = new ArrayList<>();
        ArrayList<MatchData> column3MatchesList = new ArrayList<>();
        MatchData matchData1 = null;
        MatchData matchData2 = null;
        MatchData matchData3 = null;
        MatchData matchData4 = null;
        PlayerEntity playerSix = null;
        PlayerEntity playerEight = null;

        for (int e = 0; e < playerEntities.size(); e++) {
            switch (e) {
                case (0):
                    break;
                case (1):
                    break;
                case (2):
                    break;
                case (3):
                    //minimum of 4 players needed to start a tournament
                    matchData1 = new MatchData(1, playerEntities.get(0), playerEntities.get(1));
                    matchData2 = new MatchData(2, playerEntities.get(2), playerEntities.get(3));
                    Log.d(TAG, "setData: case 3");
                    break;
                case (4):
                    //set up 'Bye' round if only 5 players present
                    if (playerEntities.size() < 6){
                        Log.d(TAG, "setData: case 4, in if statement");
                        playerSix = new PlayerEntity("6", "TBD");
                        matchData3 = new MatchData(3, playerEntities.get(4), playerSix);
                    }
                    break;
                case (5):
                    Log.d(TAG, "setData: case 5");
                    matchData3 = new MatchData(3, playerEntities.get(4), playerEntities.get(5));
                    break;
                case (6):
                    //Bye round for match 4
                    Log.d(TAG, "setData: case 6");
                    if (playerEntities.size() < 8){
                        playerEight = new PlayerEntity("8", "TBD");
                        matchData4 = new MatchData(4, playerEntities.get(4), playerEight);
                    }
                    break;
                case (7):
                    Log.d(TAG, "setData: case 7");
                    matchData4 = new MatchData(4, playerEntities.get(6), playerEntities.get(7));
                    break;
                default:
                    throw new IndexOutOfBoundsException("no player data in aray");
            }
        }

        if (matchData1 != null) {
            Log.d(TAG, "setData: set match1");
            column1MatchesList.add(matchData1);
        }
        if (matchData2 != null) {
            Log.d(TAG, "setData: set match2");
            column1MatchesList.add(matchData2);
        }
        if (matchData3 != null) {
            Log.d(TAG, "setData: set match3");
            column1MatchesList.add(matchData3);
        }
        if (matchData4 != null) {
            Log.d(TAG, "setData: set match4");
            column1MatchesList.add(matchData4);
        }

        ColumnData column1Data = new ColumnData(column1MatchesList);
        sectionList.add(column1Data);
        //END COLUMN 1 DATA, START COLUMN TWO - to be generated programmatically based on wins

        PlayerEntity playerNine = new PlayerEntity("9", "TBD");
        PlayerEntity playerTen = new PlayerEntity("10","TBD");
        PlayerEntity playerEleven = new PlayerEntity("11", "TBD");
        PlayerEntity playerTwelve = new PlayerEntity("12","TBD");
        MatchData matchData5 = new MatchData(5, playerNine, playerTen);
        MatchData matchData6 = new MatchData(6, playerEleven, playerTwelve);
        column2MatchesList.add(matchData5);
        column2MatchesList.add(matchData6);
        columnData2 = new ColumnData(column2MatchesList);
        sectionList.add(columnData2);
        PlayerEntity competitorThirteen = new PlayerEntity("13","TBD");
        PlayerEntity competitorFourteen = new PlayerEntity("14","TBD");
        MatchData matchData7 = new MatchData(7, competitorThirteen, competitorFourteen);

        /*staticMatchesList.add(new MatchEntity( 5, playerNine.getId(), playerTen.getId()));
        staticMatchesList.add(new MatchEntity(6, playerEleven.getId(), playerTwelve.getId()));
        staticMatchesList.add(new MatchEntity(7, competitorThirteen.getId(), competitorFourteen.getId()));*/

        column3MatchesList.add(matchData7);
        //END COLUMN 2 DATA, START COLUMN 3
        ColumnData columnData3 = new ColumnData(column3MatchesList);
        sectionList.add(columnData3);

    }

    private void initViewPagerAdapter() {
        sectionAdapter = new BracketsSectionAdapter(getChildFragmentManager(), sectionList);
        viewPager.setOffscreenPageLimit(10);
        viewPager.setAdapter(sectionAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageMargin(-200);
        viewPager.setHorizontalFadingEdgeEnabled(true);
        viewPager.setFadingEdgeLength(50);
        viewPager.addOnPageChangeListener(this);
    }

    private void initViews() {
        viewPager = (WrapContentHtViewPager) getView().findViewById(R.id.frag_brackets_container);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mCurrentPagerState != ViewPager.SCROLL_STATE_SETTLING) {
            if (positionOffset > .5) {
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                    if (getBracketsFragment(position).getColumnList().get(0).getHeight()
                            != BracketsUtility.dpToPx(131))
                        getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                    if (getBracketsFragment(position + 1).getColumnList().get(0).getHeight()
                            != BracketsUtility.dpToPx(131))
                        getBracketsFragment(position + 1).shrinkView(BracketsUtility.dpToPx(131));
                }
            } else {
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    if (getBracketsFragment(position + 1).getCurrentBracketSize() ==
                            getBracketsFragment(position + 1).getPreviousBracketSize()) {
                        getBracketsFragment(position + 1).shrinkView(BracketsUtility.dpToPx(131));
                        getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                    } else {
                        int currentFragmentSize = getBracketsFragment(position + 1).getCurrentBracketSize();
                        int previousBracketSize = getBracketsFragment(position + 1).getPreviousBracketSize();
                        if (currentFragmentSize != previousBracketSize) {
                            getBracketsFragment(position + 1).expandHeight(BracketsUtility.dpToPx(262));
                            getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                        }
                    }
                }
            }
        } else {
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

    public BracketColumnFragment getBracketsFragment(int position) {
        BracketColumnFragment bracketsFrag = null;
        if (getChildFragmentManager() != null) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof BracketColumnFragment) {
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
