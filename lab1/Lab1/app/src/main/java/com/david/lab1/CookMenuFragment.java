package com.david.lab1;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Fragment where the cook can view and edit the menu
 */
public class CookMenuFragment extends Fragment {

    @BindView(R.id.addFAB) FloatingActionButton addFAB;
    @BindView(R.id.menuListView) ListView menuListView;

    private ArrayList<MenuItem> menuList;
    private ArrayAdapter<MenuItem> menuAdapter;

    public CookMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cook_menu, container, false);
        ButterKnife.bind(this, view);

        menuList = new ArrayList<>();
        menuAdapter = new MenuAdapter(getContext(), menuList);
        menuListView.setAdapter(menuAdapter);

        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                alertDialogBuilder.setTitle(R.string.new_menu_item);
                final EditText input = new EditText(view.getContext());
                input.setHint(R.string.name);
                alertDialogBuilder.setView(input);

                alertDialogBuilder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        menuList.add(new MenuItem(text));
                        menuAdapter.notifyDataSetChanged();
                    }
                });
                alertDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialogBuilder.show();
            }
        });

        return view;
    }

}
