package com.david.lab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for displaying a MenuItem in a ListView
 */

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    @BindView(R.id.textView) TextView textView;

    public MenuAdapter(Context context, ArrayList<MenuItem> menu) {
        super(context, 0, menu);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }
        ButterKnife.bind(this, convertView);

        textView.setText(getItem(pos).getName());

        return convertView;
    }
}
