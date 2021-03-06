package com.example.tournamentbracketcreator.fragment;

//import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.activity.StartTournActivity;
import com.example.tournamentbracketcreator.view.StartnavViewModel;

public class StartnavFragment extends Fragment {

    public Button startTourn, viewWL, viewRatings;
    public StartnavViewModel mViewModel;
    public static final String TAG = "StartnavFragment";


    public static StartnavFragment newInstance() {
        return new StartnavFragment();
    }


    WLRecordsFragment wlRecordsFragment = new WLRecordsFragment();
    RatingsFragment ratingsFragment = new RatingsFragment();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");
        View root = inflater.inflate(R.layout.fragment_startnav, container, false);

        startTourn = root.findViewById(R.id.fragment_startnav_startTournBtn);
        viewWL = root.findViewById(R.id.fragment_startnav_resultsBtn);
        viewRatings = root.findViewById(R.id.fragment_startnav_ratingsBtn);


        startTournActivity(startTourn);
        openNewFrag(root, viewWL, wlRecordsFragment);
        openNewFrag(root, viewRatings, ratingsFragment);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }
    /*public boolean onBackPressed() {
        // currently visible tab Fragment
        OnBackPressListener currentFragment = (OnBackPressListener) vAdapter.getRegisteredFragment(viewPager.getCurrentItem());

        if (currentFragment != null) {
            // lets see if the currentFragment or any of its childFragment can handle onBackPressed
            return currentFragment.onBackPressed();
        }

        // this Fragment couldn't handle the onBackPressed call
        return false;
    }*/



    private void openNewFrag(final View view, Button button, final Fragment fragment) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(View.INVISIBLE);
//                viewPager = getActivity().findViewById(R.id.main_view_pager);
//                viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager()));
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.hide(fragment);
                fragmentTransaction.replace(R.id.login_fragment_container, fragment)
                        .addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }
    private void startTournActivity(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StartTournActivity.class);
                //intent.putExtra("inflate_poola", 1);
                startActivity(intent);
            }
        });
    }

    /*private void startTournActivity(final View view, Button button, final Fragment fragmentA,
                                    final Fragment fragmentB) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewPager = getActivity().findViewById(R.id.tourn_pool_pager);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                //fragmentTransaction.hide(fragment);
                fragmentTransaction.replace(R.id.login_fragment_container, fragmentA)
                        .addToBackStack(null);
                fragmentTransaction.commit();
//                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.add(R.id.poolb_container, fragmentB).addToBackStack("frag_poolb").commit();
            }
        });

    }*/

}
