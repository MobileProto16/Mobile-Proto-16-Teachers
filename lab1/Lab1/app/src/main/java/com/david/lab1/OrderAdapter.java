package com.david.lab1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for displaying an Order in a ListView.  Displays each item in the order, as well as the
 * customer that ordered it.
 */

public class OrderAdapter extends ArrayAdapter<Order> {

    @BindView(R.id.orderItemTextView) TextView orderItemTextView;
    @BindView(R.id.customerTextView) TextView customerTextView;

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        super(context, 0, orders);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
        }
        ButterKnife.bind(this, convertView);

        Order order = getItem(pos);

        orderItemTextView.setText(order.getItems().toString());
        Log.d("WONG", order.getItems().toString());
        customerTextView.setText(order.getCustomer().getName());

        return convertView;
    }
}
