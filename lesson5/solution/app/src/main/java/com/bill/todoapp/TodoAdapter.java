package com.bill.todoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bill on 7/24/16.
 */
public class TodoAdapter extends ArrayAdapter<Todo> {

    public TodoAdapter(Context context, ArrayList<Todo> todos) {
        super(context, 0, todos);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        final Todo todo = getItem(pos);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo, parent, false);
        }

        final TextView textView = (TextView) convertView.findViewById(R.id.textView);
        final Button completeButton = (Button) convertView.findViewById(R.id.completeButton);

        textView.setText(todo.text);
        completeButton.setText(todo.completed ? "Undone": "Done");
        textView.setText(todo.completed ? String.format("%s (Completed)", todo.text) : todo.text);

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo.completed = !todo.completed;
                textView.setText(todo.completed ? String.format("%s (Completed)", todo.text) : todo.text);
                completeButton.setText(todo.completed ? "Undone": "Done");
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Item");

                // Input text field to edit item
                final EditText input = new EditText(getContext());
                input.setText(todo.text);
                builder.setView(input);

                // Buttons
                // Change the list items when edit is clicked
                builder.setNeutralButton("Cancel", null);
                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todo.text = input.getText().toString();
                        textView.setText(todo.text);
                    }
                });
                builder.show();
            }
        });

        return convertView;
    }
}
