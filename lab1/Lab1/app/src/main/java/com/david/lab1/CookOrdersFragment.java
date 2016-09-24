package com.david.lab1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Fragment where the cook can see all their orders.
 */
public class CookOrdersFragment extends Fragment {

    @BindView(R.id.ordersListView) ListView ordersListView;

    private ArrayList<Order> orderList;
    private OrderAdapter orderAdapter;

    public CookOrdersFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cook_orders, container, false);
        ButterKnife.bind(this, view);

        //TODO get this from S3 instead of pre-populating
        orderList = new ArrayList<>();
        Customer david = new Customer("David");
        ArrayList<MenuItem> orderItems = new ArrayList<>();
        orderItems.add(new MenuItem("Burger"));
        orderItems.add(new MenuItem("Pizza"));
        orderList.add(new Order(david, orderItems));

        orderAdapter = new OrderAdapter(getContext(), orderList);
        ordersListView.setAdapter(orderAdapter);

        return view;
    }

}
