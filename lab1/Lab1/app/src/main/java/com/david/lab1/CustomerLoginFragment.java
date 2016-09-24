package com.david.lab1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by david on 9/23/16.
 */

public class CustomerLoginFragment extends Fragment {

    @BindView(R.id.backButton) Button back;
    @BindView(R.id.customerLogin) Button login;
    @BindView(R.id.editText) EditText name;
    private static final String tag = CustomerLoginFragment.class.getName();

    private MainActivity myActivity;
    private ArrayList<Customer> customers;

    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String typedText = name.getText().toString();
            Customer currentCustomer = null;
            for (Customer c : customers) {
                if (c.getName().equalsIgnoreCase(typedText))
                    currentCustomer = c;
            }
            if (currentCustomer == null) {
                currentCustomer = new Customer(typedText);
                customers.add(currentCustomer);
            }
            myActivity.saveCustomersToS3();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_login, container, false);
        ButterKnife.bind(this, v);
        myActivity = (MainActivity) getActivity();
        myActivity.loadCustomersFromS3();
        customers = myActivity.getCustomers();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myActivity.goBack();
            }
        });

        login.setOnClickListener(loginListener);

        return v;
    }

}
