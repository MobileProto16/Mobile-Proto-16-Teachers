package com.david.lab1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by david on 9/23/16.
 */

public class CustomerLoginFragment extends Fragment {

    private MainActivity myActivity;
    private ArrayList<Customer> customers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_login, container, false);

        myActivity = (MainActivity) getActivity();
        customers = myActivity.getCustomers();

        return v;
    }

}
