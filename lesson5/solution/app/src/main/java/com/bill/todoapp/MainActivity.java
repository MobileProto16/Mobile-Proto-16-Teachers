package com.bill.todoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button addButton;
    private ListView listView;
    private ArrayList todos;
    public ArrayAdapter<Todo> todoAdapter;
    TodoDBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        addButton = (Button) findViewById(R.id.addButton);
        listView = (ListView) findViewById(R.id.listView);
        todos = getSavedTodos();
        todoAdapter = new TodoAdapter(this, todos);

        listView.setAdapter(todoAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todos.add(new Todo(editText.getText().toString()));
                mDBHelper = new TodoDBHelper(getBaseContext());
                mDBHelper.writeToDatabase(editText.getText().toString());
                editText.setText("");
                todoAdapter.notifyDataSetChanged();
            }
        });
    }

    public ArrayList getSavedTodos() {
        mDBHelper = new TodoDBHelper(getBaseContext());
        ArrayList<String> todoNames = new ArrayList<String>();
        todoNames = mDBHelper.readDatabase();
        todos = new ArrayList<>();
        for (String item : todoNames) {
            todos.add(new Todo(item));
        }
        return todos;
    }
}
