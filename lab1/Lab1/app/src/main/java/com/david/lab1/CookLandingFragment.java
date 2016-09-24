package com.david.lab1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Landing page for the cook where you can go to the menu or your orders.
 */
public class CookLandingFragment extends Fragment {

    @BindView(R.id.orderButton) Button orderButton;
    @BindView(R.id.menuButton) Button menuButton;

    private MainActivity mainActivity;

    public CookLandingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cook_landing, container, false);
        ButterKnife.bind(this, view);
        mainActivity = (MainActivity) getActivity();

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.switchFragment(new CookOrdersFragment());
            }
        });
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.switchFragment(new CookMenuFragment());
            }
        });

        return view;
    }

}
