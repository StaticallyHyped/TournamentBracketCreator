package com.example.tournamentbracketcreator.view;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;

import android.util.Log;

import com.amazonaws.amplify.generated.graphql.ListTtPlayersQuery;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.tournamentbracketcreator.model.ClientFactory;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class StartnavViewModel extends AndroidViewModel {

    public static final String TAG = "StartnavViewModel";

    private AWSAppSyncClient mAWSAppSyncClient;
    //moved to RVAdapter
    public List<ListTtPlayersQuery.Item> playersArrayList = new ArrayList<>();
    //private RecyclerView mRView;
    //private TournPoolRVAdapter adapter;

    public StartnavViewModel(@NonNull Application application) {
        super(application);
    }

    public AWSAppSyncClient getmAWSAppSyncClient() {
        return mAWSAppSyncClient;
    }

    public List<ListTtPlayersQuery.Item> getPlayersArrayList() {
        return playersArrayList;
    }

    public GraphQLCall.Callback<ListTtPlayersQuery.Data> getPlayersCallback() {
        return playersCallback;
    }

    private void query(){
        if (mAWSAppSyncClient == null){
            mAWSAppSyncClient = ClientFactory.getInstance(getApplication().getApplicationContext());
           // mAWSAppSyncClient = ClientFactory.getInstance(StartTournActivity.class);
        }
        mAWSAppSyncClient.query(ListTtPlayersQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(playersCallback);
    }

    private GraphQLCall.Callback<ListTtPlayersQuery.Data> playersCallback = new
            GraphQLCall.Callback<ListTtPlayersQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<ListTtPlayersQuery.Data> response) {
                    if (response.data() != null) {
                        /*players = response.data().listTTPlayers().items();*/
                    } else {
//                        players = new ArrayList<>();
                    }
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.d(TAG, "onFailure: Failed to make players api call");
                    Log.d(TAG, "onFailure" + e.toString());
                }
            };
}
