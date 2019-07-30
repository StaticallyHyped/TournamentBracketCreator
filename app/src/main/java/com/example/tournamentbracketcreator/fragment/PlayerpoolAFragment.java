package com.example.tournamentbracketcreator.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.amazonaws.amplify.generated.graphql.ListTtPlayersQuery;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.adapter.TournPoolRVAdapter;
import com.example.tournamentbracketcreator.adapter.ViewPagerAdapter;
import com.example.tournamentbracketcreator.model.ClientFactory;
import com.example.tournamentbracketcreator.view.PlayerpoolAViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class PlayerpoolAFragment extends Fragment {
    public static final String TAG = "PlayerpoolAFragment";

    private PlayerpoolAViewModel mViewModel;
    private String title;
    private int page;
    public ViewPager viewPager;
    public ViewPagerAdapter vAdapter;
    RecyclerView mRecyclerView;
    TournPoolRVAdapter mAdapter;
    private AWSAppSyncClient mAWSAppSynClient;
    //from StartTournActivity
    private List<ListTtPlayersQuery.Item> players = new ArrayList<>();

    public static PlayerpoolAFragment newInstance(int page, String title) {
        PlayerpoolAFragment poolAFrag = new PlayerpoolAFragment();
        Bundle args = new Bundle();
        args.putInt("intKey", page);
        args.putString("stringKey", title);
        poolAFrag.setArguments(args);
        return poolAFrag;
    }

    public Button addToList, removeFromList;
    public ImageButton addToTournPool, removeFromTournPool;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //good for initializing any data you may want to
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("stringTitle");

        //try fiddling with this method (context) if it doesn't work
        //TODO test to see if running ClientFactory.getInstance() in the query method works w/o
        //instantiation here in the onCreate
        ClientFactory.getInstance(getContext());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");
        View root = inflater.inflate(R.layout.fragment_playerpoola, container, false);

        addToList = root.findViewById(R.id.add_new_playerBtn);
        removeFromList = root.findViewById(R.id.remove_playerBtn);
        addToTournPool = root.findViewById(R.id.add_to_tournBtn);
        removeFromTournPool = root.findViewById(R.id.remove_from_tournBtn);

        mRecyclerView = root.findViewById(R.id.poola_RV);
        //From dev.to/kkemple
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new TournPoolRVAdapter(getContext());

        //from appsync-app

        mRecyclerView.setAdapter(mAdapter);
        //StartnavViewModel model = ViewModelProviders.of(this).get(StartnavViewModel.class);

        Log.d(TAG, "onCreateView: exits");
//        ClientFactory.getInstance(getContext());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        query();
    }

    public void query() {
        if (mAWSAppSynClient == null) {
            Log.d(TAG, "query: mAWSAppSyncClient is null - creating new one");
            mAWSAppSynClient = ClientFactory.getInstance(getContext());
        }
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
                        Log.d(TAG, "onResponse: .data.listTTplayers: " + response.data().listTTPlayers());
                        players = new ArrayList<>(response.data().listTTPlayers().items());
                        Log.d(TAG, "onResponse: after setting value of players");
                    } else {
                        Log.d(TAG, "onResponse: response.data is null, creating new ArrayList");
                        players = new ArrayList<>();
                    }
                    Log.d(TAG, "onResponse: starting mAdapter.setItems(players)");
                    mAdapter.setItems(players);
                    //mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.d(TAG, "onFailure: starts");
                }
            };

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    for things you want to initialize when you're sure the activity is fully created
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlayerpoolAViewModel.class);
        // TODO: Use the ViewModel
    }*/

}
