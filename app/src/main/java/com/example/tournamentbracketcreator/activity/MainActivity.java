package com.example.tournamentbracketcreator.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.amazonaws.amplify.generated.graphql.ListTtPlayersQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.LoginFragment;

import javax.annotation.Nonnull;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    public static final String TAG = "MainActivity";
    public LoginFragment loginFragment = new LoginFragment();
    //TODO eventually add login/password functionality on MainActivity or LoginFragmentViewModel
    private AWSAppSyncClient mAWSAppSyncClient;

    private static final String BASE_URL = "https://et4lknhrqfbbrlridkhhjgxefe.appsync-api.us-west-2.amazonaws.com/graphql";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                // If you are using complex objects (S3) then uncomment
                //.s3ObjectManager(new S3ObjectManagerImplementation(new AmazonS3Client(AWSMobileClient.getInstance())))
                .build();
/* TODO Add functionality for login/user access for LoginFragment
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();*/

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTran = fm.beginTransaction();
        fragmentTran.replace(R.id.login_fragment_container, loginFragment, "login_frag")
                .addToBackStack("login_frag").commit();
        
        //query();
        Log.d(TAG, "onCreate: After Query");
        Log.d(TAG, "onCreate: afterInitLoginFrag");
    }

   /* public void query(){
        Log.d(TAG, "query: Starts");
        mAWSAppSyncClient.query(ListTtPlayersQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(playersCallback);
    }

    private GraphQLCall.Callback<ListTtPlayersQuery.Data> playersCallback = new GraphQLCall.Callback<ListTtPlayersQuery.Data>() {
        @Override
        public void onResponse(@Nonnull Response<ListTtPlayersQuery.Data> response) {
            Log.d(TAG, "onResponse: Results: " + response.data().listTTPlayers().items().toString());
            Log.d("Results", response.data().listTTPlayers().items().toString());
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.d(TAG, "onFailure: " + e.toString());
            Log.d("ERROR", e.toString());
        }
    };*/

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: pressed!");
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            Log.d(TAG, "onBackPressed: backstack has this many entries:" +
                    getSupportFragmentManager().getBackStackEntryCount());
            getFragmentManager().popBackStack();
            super.onBackPressed();
        } else {
            Log.d(TAG, "onBackPressed: backstack has this many entries: " +
                    getSupportFragmentManager().getBackStackEntryCount());
            Log.d(TAG, "onBackPressed: Fewer than 2");
//            fm.beginTransaction().replace(R.id.login_fragment_container, new LoginFragment())
//                    .commit();
            finish();
        }
    }

    public void onHomePressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            Log.d(TAG, "onHomePressed: backstack has this many entries:" +
                    getSupportFragmentManager().getBackStackEntryCount());
            //getFragmentManager().popBackStack();
            super.onBackPressed();
        } else {
            Log.d(TAG, "onHomePressed: backstack has this many entries: " +
                    getSupportFragmentManager().getBackStackEntryCount());
            Log.d(TAG, "onHomePressed: Fewer than 2");
            /*fm.beginTransaction().replace(R.id.login_fragment_container, new LoginFragment())
                    .commit();*/
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: Start");
        super.onResume();
        setScreenSize();
    }

    public void shouldDisplayHomeUp() {
        boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canGoBack);
    }

    private void setScreenSize() {
        Log.d(TAG, "setScreenSize: Start");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        // TODO create BracketsApplication class
        //  BracketsApplication.getInstance().setScreenHeight(height);
    }


    //TODO add toolbar (?)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG, "onOptionsItemSelected: item selected");
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Log.d(TAG, "onOptionsItemSelected: home button pressed");
                onHomePressed();
                Log.d(TAG, "onOptionsItemSelected: item" + item);
                return true;
            case R.id.action_settings:
                Log.d(TAG, "onOptionsItemSelected: case:settings");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }
}
