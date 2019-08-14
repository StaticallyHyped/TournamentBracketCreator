package com.example.tournamentbracketcreator.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazonaws.amplify.generated.graphql.GetTtPlayerQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.AppSyncQueryCall;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.tournamentbracketcreator.adapter.BracketsSectionAdapter;
import com.example.tournamentbracketcreator.model.ClientFactory;
import com.example.tournamentbracketcreator.model.ColumnData;
import com.example.tournamentbracketcreator.model.CompetitorData;
import com.example.tournamentbracketcreator.model.Data;
import com.example.tournamentbracketcreator.model.MatchData;
import com.example.tournamentbracketcreator.model.RoundData;
import com.example.tournamentbracketcreator.utility.BracketsUtility;
import com.example.tournamentbracketcreator.view.BracketsViewModel;
import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.view.WrapContentHtViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class BracketsFragment extends Fragment implements ViewPager.OnPageChangeListener {
    public static final String TAG = "BracketsFragment";

    private BracketsViewModel mViewModel;
    private WrapContentHtViewPager viewPager;
    private BracketsSectionAdapter sectionAdapter;
    private ArrayList<ColumnData> sectionList;
    private ArrayList<ColumnData> playerSection;
    private int mNextSelectedScreen;
    private int mCurrentPagerState;
    private AWSAppSyncClient mAWSAppSyncClient;
    public static ArrayList<String[]> tournPool = Data.getTournPoolList();
    //private List<GetTtPlayerQuery> playersData = new ArrayList<>();
    // private AppSyncQueryCall<GetPlayerByNameQuery.GetPlayerByName> queryCall;
    //private List<GetPlayerByNameQuery.GetPlayerByName> playersNameData = new ArrayList<>();
    private List<GetTtPlayerQuery.GetTTPlayer> playersNameData = new ArrayList<>();

    public static BracketsFragment newInstance() {
        return new BracketsFragment();
    }

    @Override
    public void onAttach(Context context) {
        query();
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");
        ClientFactory.getInstance(getContext());
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getContext())
                .awsConfiguration(new AWSConfiguration(getContext()))
                .build();
        //query();
        return inflater.inflate(R.layout.fragment_brackets, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BracketsViewModel.class);
        //query();

    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume: starts");
        super.onResume();

        setData();
        initViews();
        initViewPagerAdapter();
        //viewPager.notify();

        //viewPager.addOnAdapterChangeListener();



        //initViewPagerAdapter();
    }

    public void query() {
        //Index 0 is the name, index 1 is the player id
        //Loop through array to get the values from index 0 and 1
        for (String[] i : tournPool) {
            Log.d(TAG, "query: name: " + i[0]);
            String name = i[0];
            String id = i[1];

            if (mAWSAppSyncClient == null) {
                Log.d(TAG, "query: client != null");
                mAWSAppSyncClient = ClientFactory.getInstance(getContext());
            }
            Log.d(TAG, "query: starts");
            mAWSAppSyncClient.query(GetTtPlayerQuery.builder()
                    .name(name)
                    .id(id)
                    .build())
                    .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                    .enqueue(playersCallback);
            Log.d(TAG, "query: exiting");
        }
    }

    private GraphQLCall.Callback<GetTtPlayerQuery.Data> playersCallback =
            new GraphQLCall.Callback<GetTtPlayerQuery.Data>() {

                @Override
                public void onResponse(@Nonnull Response<GetTtPlayerQuery.Data> response) {
                    Log.d(TAG, "onResponse: starts");
                    if (response.data() != null) {
                        Log.d(TAG, "onResponse: starts, not null");
                        //New player object created for each loop through the callback
                        GetTtPlayerQuery.GetTTPlayer player = response.data().getTTPlayer();
                        Log.d(TAG, "onResponse: player: " + player);
                        //Players object added to the arraylist from the callback
                        playersNameData.add(player);
                        Log.d(TAG, "onResponse: playersNameData array: " + playersNameData.toString());
                        //needs to be a player object, not a string
                        // playersData.add(player);

                    } else {
                        Log.d(TAG, "onResponse: starts, response.data == null");
                    }
                    //add adapter?
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.d(TAG, "onFailure: " + e);
                }
            };


    //TODO
    // GetTtPlayerQuery to get player data from DB, initialize
    // playerOne, Two, etc with that data.

    private void setPlayerData() {
        Log.d(TAG, "setPlayerData: starts");
        playerSection = new ArrayList<>();
        ArrayList<RoundData> round1Matches = new ArrayList<>();
        ArrayList<RoundData> round2Matches = new ArrayList<>();
        ArrayList<RoundData> round3Matches = new ArrayList<>();

        //Create new PlayerData object - use getters and setters
        /*PlayerData playerOne = new PlayerData();
        PlayerData playerOne = new PlayerData();
        PlayerData playerTwo = new PlayerData();
        PlayerData playerThree = new PlayerData();
        PlayerData playerFour = new PlayerData();
        PlayerData playerFive = new PlayerData();
        PlayerData playerSix = new PlayerData();
        PlayerData playerSeven = new PlayerData();
        PlayerData playerEight = new PlayerData();

        //randomly select players to player1, player2, player3, etc

        RoundData roundData1 = new RoundData(playerOne, playerTwo);
        RoundData roundData2 = new MatchData(playerThree, playerFour);
        RoundData roundData3 = new MatchData(playerFive, playerSix);
        RoundData roundData4 = new MatchData(playerSeven, playerEight);

        //

        */
    }

    private void setData() {
        Log.d(TAG, "setData: starts");
        sectionList = new ArrayList<>();
        ArrayList<MatchData> column1MatchesList = new ArrayList<>();
        ArrayList<MatchData> column2MatchesList = new ArrayList<>();
        ArrayList<MatchData> column3MatchesList = new ArrayList<>();
        //Retrieve CompetitorData at their given index and set score to 0
        String competitor = "competitor";
        CompetitorData competitorOne = null;
        CompetitorData competitorTwo = null;
        CompetitorData competitorThree = null;
        CompetitorData competitorFour = null;
        CompetitorData competitorFive = null;
        CompetitorData competitorSix = null;
        CompetitorData competitorSeven = null;
        CompetitorData competitorEight = null;
        MatchData matchData1 = null;
        MatchData matchData2 = null;
        MatchData matchData3 = null;
        MatchData matchData4 = null;

        for (int e = 0; e < playersNameData.size(); e++) {
            switch (e) {
                case (0):
                    Log.d(TAG, "setData: case 0");
                    competitorOne = new CompetitorData(playersNameData.get(e).name(), "0");
                    break;
                case (1):
                    Log.d(TAG, "setData: case 1");
                    competitorTwo = new CompetitorData(playersNameData.get(e).name(), "0");
                    if (competitorOne != null) {
                        matchData1 = new MatchData(competitorOne, competitorTwo);
                    } else {
                        matchData1 = new MatchData(new CompetitorData("Uh Oh, Something Went Wrong", "0"),
                                competitorTwo);
                    }
                    break;
                case (2):
                    Log.d(TAG, "setData: case 2");
                    competitorThree = new CompetitorData(playersNameData.get(e).name(), "0");
                    break;
                case (3):
                    Log.d(TAG, "setData: case 3");
                    competitorFour = new CompetitorData(playersNameData.get(e).name(), "0");
                    if (competitorFour != null) {
                        matchData2 = new MatchData(competitorThree, competitorFour);
                    } else {
                        matchData2 = new MatchData(new CompetitorData("Uh oh, something went wrong", "0"),
                                competitorFour);
                    }
                    break;
                case (4):
                    Log.d(TAG, "setData: case 4");
                    competitorFive = new CompetitorData(playersNameData.get(e).name(), "0");
                    break;
                case (5):
                    Log.d(TAG, "setData: case 5");
                    competitorSix = new CompetitorData(playersNameData.get(e).name(), "0");
                    if (competitorFive != null) {
                        matchData3 = new MatchData(competitorFive, competitorSix);
                    } else {
                        matchData3 = new MatchData(new CompetitorData("Uh oh, something went wrong", "0"),
                                competitorSix);
                    }
                    break;
                case (6):
                    Log.d(TAG, "setData: case 6");
                    competitorSeven = new CompetitorData(playersNameData.get(e).name(), "0");
                    break;
                case (7):
                    competitorEight = new CompetitorData(playersNameData.get(e).name(), "0");
                    if (competitorSeven != null) {
                        matchData4 = new MatchData(competitorSeven, competitorEight);
                    } else {
                        matchData3 = new MatchData(new CompetitorData("Uh oh, something went wrong", "0"),
                                competitorEight);
                    }
                    break;
                default:
                    throw new IndexOutOfBoundsException("no player data in aray");
            }
        }

        Log.d(TAG, "setData: names loop: ");
        //CompetitorData competitorOne = new CompetitorData(playersNameData.get(0).name(), "0");
        //CompetitorData competitorTwo = new CompetitorData(playersNameData.get(1).name(), "0");
        //CompetitorData competitorThree = new CompetitorData(playersNameData.get(2).name(), "0");
        //CompetitorData competitorFour = new CompetitorData("Name 4", "4");
        /*CompetitorData competitorFive = new CompetitorData("Name 5", "1");
        CompetitorData competitorSix = new CompetitorData("Name 6", "1");
        CompetitorData competitorSeven = new CompetitorData("Name 7", "1");
        CompetitorData competitorEight = new CompetitorData("Name 8", "1");*/
//      randomly select competitors to be comp1, comp2, comp3, etc

        
        /*MatchData matchData2 = new MatchData(competitor, competitorFour);
        MatchData matchData3 = new MatchData(competitorFive, competitorSix);
        MatchData matchData4 = new MatchData(competitorSeven, competitorEight);*/
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

        
        /*column1MatchesList.add(matchData1);
        column1MatchesList.add(matchData2);
        column1MatchesList.add(matchData3);
        column1MatchesList.add(matchData4);*/
        ColumnData column1Data = new ColumnData(column1MatchesList);
        sectionList.add(column1Data);
        //END COLUMN 1 DATA, START COLUMN TWO - to be generated programmatically based on wins
        CompetitorData competitorNine = new CompetitorData("Joe Rogers", "8");
        CompetitorData competitorTen = new CompetitorData("Chelsea Clinton", "11");
        CompetitorData competitorEleven = new CompetitorData("Oscar Wilde", "14");
        CompetitorData competitorTwelve = new CompetitorData("Spindly Pete", "12");

        MatchData matchData5 = new MatchData(competitorNine, competitorTen);
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

    private void initViewPagerAdapter() {
        Log.d(TAG, "initViewPagerAdapter: starts");
        sectionAdapter = new BracketsSectionAdapter(getChildFragmentManager(), this.sectionList);
        viewPager.setOffscreenPageLimit(10);
        viewPager.setAdapter(sectionAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageMargin(-200);
        viewPager.setHorizontalFadingEdgeEnabled(true);
        viewPager.setFadingEdgeLength(50);
        viewPager.addOnPageChangeListener(this);
        /*try {
            viewPager.wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    private void initViews() {
        Log.d(TAG, "initViews: starts");
        viewPager = (WrapContentHtViewPager) getView().findViewById(R.id.frag_brackets_container);
        /*try {
            viewPager.wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
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
