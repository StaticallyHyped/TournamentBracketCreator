package com.example.tournamentbracketcreator.db;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.amazonaws.amplify.generated.graphql.ListTtPlayersQuery;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.tournamentbracketcreator.entity.CompetitorEntity;
import com.example.tournamentbracketcreator.entity.PlayerEntity;
import com.example.tournamentbracketcreator.entity.WinEntity;
import com.example.tournamentbracketcreator.model.ClientFactory;
import com.example.tournamentbracketcreator.model.Competitor;
import com.example.tournamentbracketcreator.model.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

public class DataGenerator {
    AWSAppSyncClient mAWSAppSynClient;

    public static final String TAG = "DataGenerator";
    Context context;

    public static List<PlayerEntity> players = new ArrayList<>();
    public static List<WinEntity> wins = new ArrayList<>();
    public static List<CompetitorEntity> competitors = new ArrayList<>();

    public void query() {
        if (mAWSAppSynClient == null) {
            Log.d(TAG, "query: mAWSAppSyncClient is null - creating new one");
            mAWSAppSynClient = ClientFactory.getInstance(context);
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
                        //Random rnd = new Random();
                        
                        for (int i = 0; i < players.size(); i++){
                            PlayerEntity player = new PlayerEntity();
                            CompetitorEntity competitor = new CompetitorEntity();
                            competitor.setId(players.get(i).getId());
                            competitor.setName(players.get(i).getName());
                            competitor.setScore("7");
                            player.setName(players.get(i).getName());
                            player.setId(players.get(i).getId());

                        }
                        Log.d(TAG, "onResponse: " + players);
                    } else {
                        players = new ArrayList<>();
                    }
                    //mAdapter.setItems(players);
                    //mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.d(TAG, "onFailure: starts");
                }
            };

   public static List<PlayerEntity> generatePlayers(){
       Log.d(TAG, "generatePlayers: starts");
       return players;
   }

   public static List<CompetitorEntity> generateCompetitors(){
       return competitors;
   }

   public static List<WinEntity> generateWinsForPlayers(

           final List<PlayerEntity> players) {
       Random rnd = new Random();
       for (Player player : players){
           int winsNumber = rnd.nextInt(11);
           for (int i = 0; i < winsNumber; i++){
               WinEntity win = new WinEntity();
               win.setPlayerId(player.getId());
               //Win dummy data, random number of 0 - 11
               win.setText(Integer.toString(winsNumber));
               win.setPostedAt(new Date(System.currentTimeMillis()
                       - TimeUnit.DAYS.toMillis(winsNumber - i) + TimeUnit.HOURS.toMillis(i)));
               wins.add(win);

           }
       }
       return wins;

   }


}
