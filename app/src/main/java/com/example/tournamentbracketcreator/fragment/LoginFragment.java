package com.example.tournamentbracketcreator.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.view.LoginViewModel;

public class LoginFragment extends Fragment {
    public static final String TAG = "LoginFragment";

    public LoginViewModel mViewModel;
   // public ConstraintLayout constraintLayout;
    public Button guestBtn, hostBtn;
    public TextInputEditText userName;
    public EditText password;


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        //constraintLayout = getView().findViewById(R.id.fragment_login_constraintLayout);
        guestBtn = root.findViewById(R.id.fragment_login_guestBtn);
        hostBtn = root.findViewById(R.id.fragment_login_hostBtn);
        userName = root.findViewById(R.id.fragment_login_usernameTE);
        password = root.findViewById(R.id.fragment_login_passwordTE);
        openStartnavFragment(root);
        //openFragment(root);

        Log.d(TAG, "onCreateView: start");
        return root;
        
    }

    public boolean canClose(){
        return false;
    }
    public interface FragmentChangeListener
    {
        public void replaceFragment(Fragment fragment);
    }

    /*public void openFragment (final View view){

        StartnavFragment startnavFragment = new StartnavFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        sharedViewModel.openNewFrag(view, hostBtn, fragmentTransaction, login_fragment_container, startnavFragment);

    }*/

    public void openStartnavFragment (final View view){

        Log.d(TAG, "openStartnavFragment: button pressed");
        hostBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StartnavFragment startnavFragment = new StartnavFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                fragmentTransaction.replace(R.id.login_fragment_container, startnavFragment)
                .addToBackStack(null);
                Log.d(TAG, "onClick: added to backstack");
                fragmentTransaction.commit();
                view.setVisibility(View.INVISIBLE);
            }
        });
        Log.d(TAG, "openStartnavFragment: exiting");

    }



   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: start");
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "onActivityCreated: exit");
        // TODO: Use the ViewModel
    }
*/


}
