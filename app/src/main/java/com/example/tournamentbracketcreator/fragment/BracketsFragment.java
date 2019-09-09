package com.example.tournamentbracketcreator.fragment;

//import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.amazonaws.amplify.generated.graphql.GetTtPlayerQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.AppSyncQueryCall;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.tournamentbracketcreator.adapter.BracketsCellAdapter;
import com.example.tournamentbracketcreator.adapter.BracketsSectionAdapter;
import com.example.tournamentbracketcreator.db.AppDatabase;
import com.example.tournamentbracketcreator.entity.MatchEntity;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.model.ClientFactory;
import com.example.tournamentbracketcreator.model.ColumnData;
import com.example.tournamentbracketcreator.model.CompetitorData;
import com.example.tournamentbracketcreator.model.Data;
import com.example.tournamentbracketcreator.model.MatchData;
import com.example.tournamentbracketcreator.model.PlayerData;
import com.example.tournamentbracketcreator.model.RoundData;
import com.example.tournamentbracketcreator.utility.BracketsUtility;
import com.example.tournamentbracketcreator.view.BracketsViewModel;
import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.view.PlayerListViewModel;
import com.example.tournamentbracketcreator.view.PlayerViewModel;
import com.example.tournamentbracketcreator.view.WrapContentHtViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class BracketsFragment extends Fragment implements ViewPager.OnPageChangeListener {
    public static final String TAG = "BracketsFragment";

//    private BracketsViewModel mViewModel;
    private WrapContentHtViewPager viewPager;
    private static BracketsSectionAdapter sectionAdapter;
    private static ArrayList<ColumnData> sectionList;
    private ArrayList<ColumnData> playerSection;
    private int mNextSelectedScreen;
    private int mCurrentPagerState;
//    private AWSAppSyncClient mAWSAppSyncClient;
//    public static ArrayList<String[]> tournPool = Data.getTournPoolList();
    public CompetitorData match;
    private List<GetTtPlayerQuery.GetTTPlayer> playersNameData = new ArrayList<>();
    private PlayerViewModel mPlayerViewModel;
    private AppDatabase mDb;
    List<PlayerEntity> playerList;
    List<MatchEntity> matchList;

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
        playerList = mPlayerViewModel.mObservablePlayers.getValue();
        matchList = mPlayerViewModel.mObservableMatches.getValue();
//        initPlayers(playerList);
        subscribeUiPlayers();
        return inflater.inflate(R.layout.fragment_brackets, container, false);
    }

    public void initPlayers(List<PlayerEntity> playerEntities){
        Log.d(TAG, "initPlayers: starts");
        setPlayerData(playerEntities);
    }

   /* private void subscribeUiMatches(){
        mPlayerViewModel.mObservableMatches.observe(this, new Observer<List<MatchEntity>>() {
            @Override
            public void onChanged(List<MatchEntity> matchEntities) {
                StringBuilder sb = new StringBuilder();
                for (MatchEntity match : matchEntities){
                    sb.append(match.playerOneScore);
                    sb.append(match.playerTwoScore);
                }
            }
        });
    }*/

    private void subscribeUiPlayers(){
        Log.d(TAG, "subscribeUiPlayers: starts");
        mPlayerViewModel.mObservablePlayers.observe(this, new Observer<List<PlayerEntity>>() {
            @Override
            public void onChanged(List<PlayerEntity> playerEntities) {
                Log.d(TAG, "onChanged: starts");
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

    //Runs in onClick method in BracketsCellAdapter
    public CompetitorData getPlayer(CompetitorData competitor){
        Log.d(TAG, "getPlayer: starts");
       // updateMatch(competitor);
        return competitor;
    }

//    MatchData matchData5;
    ArrayList<MatchData> column2MatchesList = new ArrayList<>();
    ColumnData columnData2 = new ColumnData(column2MatchesList);

    public void subscribeUiMatch(){
        mPlayerViewModel.mObservableMatches.observe(this, new Observer<List<MatchEntity>>() {
            @Override
            public void onChanged(List<MatchEntity> matchEntities) {

            }
        });
    }

    public void updateMatch(){


    }
    @Override
    public void onResume() {
        Log.d(TAG, "onResume: starts");
        super.onResume();

    }

    private void setPlayerData(final @NonNull List<PlayerEntity> playerEntities) {
        Log.d(TAG, "setPlayerData: starts");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        playerSection = new ArrayList<>();

        PlayerData playerOne = null;
        PlayerData playerTwo = null;
        PlayerData playerThree = null;
        PlayerData playerFour = null;
        PlayerData playerFive = null;
        PlayerData playerSix = null;
        PlayerData playerSeven = null;
        PlayerData playerEight = null;

        Log.d(TAG, "setPlayerData: playerEntities size: " + playerEntities.size());
        for (int e = 0; e < playerEntities.size(); e++) {
            switch (e) {
                case (0):
                    Log.d(TAG, "setPlayerData: case 0");
                    if (playerEntities.get(e).getId() != null){
                        Log.d(TAG, "setPlayerData: not null");
                        playerOne = new PlayerData(playerEntities.get(e).getId(),
                                playerEntities.get(e).getName());
                    } else {
                        Log.d(TAG, "setPlayerData: null");
                    }
                    break;
                case (1):
                    Log.d(TAG, "setPlayerData: case 1");
                    playerTwo = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    break;
                case (2):
                    Log.d(TAG, "setPlayerData: case 2");
                    playerThree = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    break;
                case (3):
                    Log.d(TAG, "setPlayerData: case 3");
                    playerFour = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    break;
                case (4):
                    Log.d(TAG, "setPlayerData: case 4");
                    playerFive = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    break;
                case (5):
                    Log.d(TAG, "setPlayerData: case 5");
                    playerSix = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    break;
                case (6):
                    Log.d(TAG, "setPlayerData: case 6");
                    playerSeven = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    break;
                case (7):
                    playerEight = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    break;
                default:
                    throw new IndexOutOfBoundsException("no player data in array");
            }
        }
        setMatchData(playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix, playerSeven, playerEight);

    }
    public void setMatchData(PlayerData playerOne, PlayerData playerTwo, PlayerData playerThree,
     PlayerData playerFour, PlayerData playerFive, PlayerData playerSix, PlayerData playerSeven, PlayerData playerEight){

        Log.d(TAG, "setMatchData: starts");
        MatchData matchData1 = null;
        MatchData matchData2 = null;
        MatchData matchData3 = null;
        MatchData matchData4 = null;

        if (playerOne != null) {
            matchData1 = new MatchData(1, playerOne, playerTwo);
            Log.d(TAG, "setMatchData: playerOne not null");
        } else {
            matchData1 = new MatchData(1, new PlayerData("1", "Test Match 1"),
                    playerTwo);
        }
        if (playerFour != null) {
            Log.d(TAG, "setMatchData: playerFour not null");
            matchData2 = new MatchData(2, playerThree, playerFour);
        } else {
            matchData2 = new MatchData(2, new PlayerData("3", "Test Match 2"),
                    playerFour);
        }
        if (playerFive != null) {
            matchData3 = new MatchData(3, playerFive, playerSix);
        } else {
            matchData3 = new MatchData(3, new PlayerData("5", "Test Match 3"),
                    playerSix);
        }
        if (playerSeven != null) {
            Log.d(TAG, "setMatchData: playerSeven not null");
            matchData4 = new MatchData(4, playerSeven, playerEight);
        } else {
            matchData4 = new MatchData(3, new PlayerData("7", "Uh oh, something went wrong"),
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

        PlayerData playerNine = new PlayerData("9", "TBD");
        Log.d(TAG, "setColumnData: competitorNine: " + playerNine.getName());
        PlayerData playerTen = new PlayerData("10","TBD");
        PlayerData playerEleven = new PlayerData("11", "TBD");
        PlayerData playerTwelve = new PlayerData("12","TBD");

        MatchData matchData5 = new MatchData(5, playerNine, playerTen);
        MatchData matchData6 = new MatchData(6, playerEleven, playerTwelve);
        column2MatchesList.add(matchData5);
        column2MatchesList.add(matchData6);
        columnData2 = new ColumnData(column2MatchesList);
        sectionList.add(columnData2);
        PlayerData competitorThirteen = new PlayerData("13","TBD");
        PlayerData competitorFourteen = new PlayerData("14","TBD");
        MatchData matchData7 = new MatchData(7, competitorThirteen, competitorFourteen);
        column3MatchesList.add(matchData7);
        //END COLUMN 2 DATA, START COLUMN 3
        ColumnData columnData3 = new ColumnData(column3MatchesList);
        sectionList.add(columnData3);

    }

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
        PlayerData playerOne = null;
        PlayerData playerTwo = null;
        PlayerData playerThree = null;
        PlayerData playerFour = null;
        PlayerData playerFive = null;
        PlayerData playerSix = null;
        PlayerData playerSeven = null;
        PlayerData playerEight = null;


        for (int e = 0; e < playerEntities.size(); e++) {
            switch (e) {
                case (0):
                    Log.d(TAG, "setData: case 0");
                    if (playerEntities.get(e).getId() != null){
                        Log.d(TAG, "setData: not null");
                        playerOne = new PlayerData(playerEntities.get(e).getId(),
                                playerEntities.get(e).getName());
                    } else {
                        Log.d(TAG, "setData: null");
                    }
                    
                    break;
                case (1):
                    Log.d(TAG, "setData: case 1");
                    playerTwo = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    if (playerOne != null) {
                        matchData1 = new MatchData(1, playerOne, playerTwo);

                    } else {
                        matchData1 = new MatchData(1, new PlayerData("1", "Uh Oh, Something Went Wrong"),
                                playerTwo);
                    }
                    break;
                case (2):
                    Log.d(TAG, "setData: case 2");
                    playerThree = new PlayerData(playerEntities.get(e).getId(),
                        playerEntities.get(e).getName());
                    break;
                case (3):
                    Log.d(TAG, "setData: case 3");
                    playerFour = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    if (playerFour != null) {
                        matchData2 = new MatchData(2, playerThree, playerFour);
                    } else {
                        matchData2 = new MatchData(2, new PlayerData("3", "Uh oh, something went wrong"),
                                playerFour);
                    }
                    break;
                case (4):
                    Log.d(TAG, "setData: case 4");
                    playerFive = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    break;
                case (5):
                    Log.d(TAG, "setData: case 5");
                    playerSix = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    if (playerFive != null) {
                        matchData3 = new MatchData(3, playerFive, playerSix);
                    } else {
                        matchData3 = new MatchData(3, new PlayerData("5", "Uh oh, something went wrong"),
                                playerSix);
                    }
                    break;
                case (6):
                    Log.d(TAG, "setData: case 6");
                    playerSeven = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    break;
                case (7):
                    playerEight = new PlayerData(playerEntities.get(e).getId(),
                            playerEntities.get(e).getName());
                    if (playerSeven != null) {
                        matchData4 = new MatchData(4, playerSeven, playerEight);
                    } else {
                        matchData3 = new MatchData(3, new PlayerData("7", "Uh oh, something went wrong"),
                                playerEight);
                    }
                    break;
                default:
                    throw new IndexOutOfBoundsException("no player data in aray");
            }
        }
        Log.d(TAG, "setData: names loop: ");

        //add to first column

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

        PlayerData playerNine = new PlayerData("9", "TBD");
        Log.d(TAG, "setData: competitorNine: " + playerNine.getName());
        PlayerData playerTen = new PlayerData("10","TBD");
        PlayerData playerEleven = new PlayerData("11", "TBD");
        PlayerData playerTwelve = new PlayerData("12","TBD");

        MatchData matchData5 = new MatchData(5, playerNine, playerTen);
        MatchData matchData6 = new MatchData(6, playerEleven, playerTwelve);
        column2MatchesList.add(matchData5);
        column2MatchesList.add(matchData6);
        columnData2 = new ColumnData(column2MatchesList);
        sectionList.add(columnData2);
        PlayerData competitorThirteen = new PlayerData("13","TBD");
        PlayerData competitorFourteen = new PlayerData("14","TBD");
        MatchData matchData7 = new MatchData(7, competitorThirteen, competitorFourteen);
        column3MatchesList.add(matchData7);
        //END COLUMN 2 DATA, START COLUMN 3
        ColumnData columnData3 = new ColumnData(column3MatchesList);
        sectionList.add(columnData3);

    }

    private void initViewPagerAdapter() {
        Log.d(TAG, "initViewPagerAdapter: starts");

        sectionAdapter = new BracketsSectionAdapter(getChildFragmentManager(), sectionList);
        Log.d(TAG, "initViewPagerAdapter: sectionList size: " + sectionList.size());
        viewPager.setOffscreenPageLimit(10);
        viewPager.setAdapter(sectionAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageMargin(-200);
        viewPager.setHorizontalFadingEdgeEnabled(true);
        viewPager.setFadingEdgeLength(50);
        viewPager.addOnPageChangeListener(this);
        Log.d(TAG, "initViewPagerAdapter: exits");
    }

    private void initViews() {
        Log.d(TAG, "initViews: starts");
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
        Log.d(TAG, "getBracketsFragment: starts");
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

    /*@Override
    public void onArrowClick(CompetitorData competitorData) {
        Log.d(TAG, "onArrowClick: clicked in BracketsFragment");
        BracketsCellAdapter.OnUpdateMatchClickListener listener = (BracketsCellAdapter
                .OnUpdateMatchClickListener) getView();
        if (listener != null){
            listener.onArrowClick(competitorData);
        }
    }*/
}
