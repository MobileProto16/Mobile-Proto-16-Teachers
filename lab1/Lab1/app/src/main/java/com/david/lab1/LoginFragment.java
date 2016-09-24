package com.david.lab1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.loginAsCustomer) Button loginCustomer;
    @BindView(R.id.loginAsCook) Button loginCook;

    private MainActivity mainActivity;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);
        mainActivity = (MainActivity) getActivity();

        loginCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.switchFragment(new CustomerLoginFragment());
            }
        });
        loginCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.switchFragment(new CookLandingFragment());
            }
        });

        return v;
    }
}
