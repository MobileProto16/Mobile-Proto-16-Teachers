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

    private OrderAdapter orderAdapter;

    public CookOrdersFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cook_orders, container, false);
        ButterKnife.bind(this, view);

        ArrayList<Order> orderList = ((MainActivity) getActivity()).getOrders();
        orderAdapter = new OrderAdapter(getContext(), orderList);
        ordersListView.setAdapter(orderAdapter);

        return view;
    }

}
