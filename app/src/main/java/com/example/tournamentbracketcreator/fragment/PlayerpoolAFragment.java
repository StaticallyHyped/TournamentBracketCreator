package com.example.tournamentbracketcreator.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.tournamentbracketcreator.activity.StartTournActivity;
import com.example.tournamentbracketcreator.adapter.PlayerAdapter;
import com.example.tournamentbracketcreator.adapter.TournPoolBRVAdapter;
import com.example.tournamentbracketcreator.adapter.TournPoolRVAdapter;
import com.example.tournamentbracketcreator.adapter.ViewPagerAdapter;
import com.example.tournamentbracketcreator.databinding.FragmentPlayerpoolaBinding;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.model.ClientFactory;
import com.example.tournamentbracketcreator.model.Data;
import com.example.tournamentbracketcreator.model.Player;
import com.example.tournamentbracketcreator.ui.PlayerClickCallback;
import com.example.tournamentbracketcreator.view.PlayerListViewModel;
import com.example.tournamentbracketcreator.view.PlayerViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class PlayerpoolAFragment extends Fragment implements AddNewPlayerDialogFragment.DialogEvents,
        View.OnClickListener {
    public static final String TAG = "PlayerpoolAFragment";
   // private PlayerAdapter mPlayerAdapter;
    public static final String KEY_PLAYER_ID = "player_id";
    private FragmentPlayerpoolaBinding mBinding;
    
    private StartTournActivity mStartTournActivity;
    private String title;
    private int page;
    public ViewPager viewPager;
    private PlayerAdapter mPlayerAdapter;
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

    public Button addToList, removeFromList, confirm, cancel;
    public ImageButton addToTournPool, removeFromTournPool;

    //from SO 1
    ClickOnFragA listener;
    public void setOnFragmentAClickListener(ClickOnFragA listener){
        this.listener = listener;
    }

    public interface ClickOnFragA{
        public void onClickOnA(int position);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        //good for initializing any data you may want to
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("stringTitle");

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new TournPoolRVAdapter(getContext());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();

        Log.d(TAG, "onCreateView: exits");
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: starts");
        super.onActivityCreated(savedInstanceState);
        //TODO add search button
        onAddNewPlayerDialog();
        onClick(addToTournPool);
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: starts");
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
                        players = new ArrayList<>(response.data().listTTPlayers().items());
                        Log.d(TAG, "onResponse: " + players);
                    } else {
                        players = new ArrayList<>();
                    }
                    mAdapter.setItems(players);
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.d(TAG, "onFailure: starts");
                }
            };

    private void showEditDialog(){
        Log.d(TAG, "showEditDialog: button clicked, start of showEditDialog");
        //TODO try not loading a new FragmentManager
        //FragmentTransaction ft = get
        FragmentManager fm = getFragmentManager();
        AddNewPlayerDialogFragment newPlayerDialog = AddNewPlayerDialogFragment
                .newInstance("Add PlayerData");
        //newPlayerDialog.setTargetFragment(PlayerpoolAFragment.this, 300);
        newPlayerDialog.show(fm, "fragment_add_player");
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mAdapter.notifyDataSetChanged();
        //newPlayerDialog.sendBackResult();
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, "onAttach: starts");
        mStartTournActivity = (StartTournActivity) activity;
        super.onAttach(activity);
        // super.onAttach(context);
    }
    @Override
    public void onClick(View view){
        Log.d(TAG, "onClick: starts");
        addToTournPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartTournActivity.buttonClicked("send to fragB");
            }
        });
    }

    public void onAddNewPlayerDialog(){
        addToList.setOnClickListener(v -> {
            showEditDialog();
        });
        Log.d(TAG, "onAddNewPlayerDialog: starts");
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        Log.d(TAG, "onFinishEditDialog: starts");
        Toast.makeText(getContext(), inputText + " has been added to the list of players", Toast.LENGTH_SHORT)
        .show();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        Log.d(TAG, "onAttachFragment: starts");
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: starts");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: starts");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: starts");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: starts");
        super.onDetach();
    }

}
