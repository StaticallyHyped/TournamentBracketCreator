package com.example.tournamentbracketcreator.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tournamentbracketcreator.view.LoginViewModel;
import com.example.tournamentbracketcreator.R;

public class LoginFragment extends Fragment {
    public static final String TAG = "LoginFragment";

    public LoginViewModel mViewModel;
    public ConstraintLayout constraintLayout;
    public Button guestBtn, hostBtn;
    public TextInputEditText userName;
    public EditText password;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: start");
        return inflater.inflate(R.layout.fragment_login, container, false);
        
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: start");
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        constraintLayout = getView().findViewById(R.id.fragment_login_constraintLayout);
        guestBtn = getView().findViewById(R.id.fragment_login_guestBtn);
        hostBtn = getView().findViewById(R.id.fragment_login_hostBtn);
        userName = getView().findViewById(R.id.fragment_login_usernameTE);
        password = getView().findViewById(R.id.fragment_login_passwordTE);
        Log.d(TAG, "onActivityCreated: exit");
        // TODO: Use the ViewModel
    }

}
