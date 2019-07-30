package com.example.tournamentbracketcreator.activity;

import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.fragment.PlayerpoolAFragment;
import com.example.tournamentbracketcreator.fragment.PlayerpoolBFragment;
import com.example.tournamentbracketcreator.model.ClientFactory;

public class StartTournActivity extends AppCompatActivity {
    public static final String TAG = "StartTournActivity";
    FragmentPagerAdapter adapterViewPager;
    //RecyclerView mRecyclerView;
    //TournPoolRVAdapter mAdapter;
    //StartnavViewModel model;
    //private AWSAppSyncClient mAWSAppSynClient;
    //private List<ListTtPlayersQuery.Item> players = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tourn);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_start_tourn, menu);
        return true;
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

}
