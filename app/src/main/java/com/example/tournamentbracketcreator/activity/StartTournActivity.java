package com.example.tournamentbracketcreator.activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.amazonaws.amplify.generated.graphql.ListTtPlayersQuery;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.PlayerpoolAFragment;
import com.example.tournamentbracketcreator.fragment.PlayerpoolBFragment;
import com.example.tournamentbracketcreator.model.ClientFactory;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class StartTournActivity extends AppCompatActivity {
    public static final String TAG = "StartTournActivity";
    FragmentPagerAdapter adapterViewPager;
    //RecyclerView mRecyclerView;
    //TournPoolRVAdapter mAdapter;
    //StartnavViewModel model;
    private AWSAppSyncClient mAWSAppSynClient;
    private List<ListTtPlayersQuery.Item> players = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tourn);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*
        StartnavViewModel model = ViewModelProviders.of(this).get(StartnavViewModel.class);

        mAdapter = new TournPoolRVAdapter(this, players);
        mRecyclerView.setAdapter(mAdapter);*/
        ClientFactory.getInstance(this);

        //TODO add poolfragments/activity to backstack
        //TODO inflate menu

        ViewPager vpPager = (ViewPager) findViewById(R.id.tourn_pool_vp);
        adapterViewPager = new PagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setClipToPadding(false);
        vpPager.setPageMargin(12);

    }

    public static class PagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return PlayerpoolAFragment.newInstance(0, "Page #1");
                case 1:
                    return PlayerpoolBFragment.newInstance(1, "Page #2");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }
        @Override
        public float getPageWidth(int position) {
        return 0.85f;
    }
    }
    @Override
    public void onResume() {
        super.onResume();
        query();
    }

    public void query() {
        if (mAWSAppSynClient == null) {
            Log.d(TAG, "query: mAWSAppSyncClient is null - creating new one");
            mAWSAppSynClient = ClientFactory.getInstance(this);
        }
        ClientFactory.appSyncClient().query(ListTtPlayersQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(playersCallback);
        mAWSAppSynClient.query(ListTtPlayersQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(playersCallback);
        Log.d(TAG, "query: enqeueing");
    }
    private GraphQLCall.Callback<ListTtPlayersQuery.Data> playersCallback =
            new GraphQLCall.Callback<ListTtPlayersQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<ListTtPlayersQuery.Data> response) {
                    if (response.data() != null){
                        Log.d(TAG, "onResponse: response.data: not null");
                        // Log.d(TAG, "onResponse: " + players.toString());
                        Log.d(TAG, "onResponse: response: " + response);
                        Log.d(TAG, "onResponse: .data: " + response.data());
                        Log.d(TAG, "onResponse: .data.listTTplayers: " + response.data().listTTPlayers());
                        try {
                            Log.d(TAG, "onResponse: TRY");
                            Log.d(TAG, "onResponse TRY: .listTTPlayers.items: " + response.data().listTTPlayers().items());
                            players = new ArrayList<>(response.data().listTTPlayers().items());
                        } catch (Exception e){
                            Log.d(TAG, "onResponse: catch block");
                        }
                        Log.d(TAG, "onResponse: after setting value of players");
                    } else {
                        Log.d(TAG, "onResponse: response.data is null, creating new ArrayList");
                        players = new ArrayList<>();
                    }
                    //Log.d(TAG, "onResponse: starting mAdapter.setItems(players)");
                    Log.d(TAG, "onResponse: EXITING onResponse");
//                    mAdapter.setItems(players);
//                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.d(TAG, "onFailure: starts");
                }
            };

}
