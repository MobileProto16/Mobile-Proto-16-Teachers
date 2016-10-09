package com.bill.todoapp;

/**
 * Created by shruti on 9/16/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import com.bill.todoapp.TodoDBSchema.TodoEntry;

public class TodoDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Todo.db";

    public TodoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TodoEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(TodoEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void writeToDatabase(String input){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TodoEntry.TODO_COLUMN, input);

        db.insertOrThrow(
                TodoEntry.TABLE_NAME,
                null,
                values);
        Log.d("Wrote to database:", TodoEntry.TABLE_NAME + "-----" + TodoEntry.TODO_COLUMN + "---" + input);
    }

    public ArrayList<String> readDatabase(){
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<String> todoList = new ArrayList<>();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                TodoEntry.TODO_ID,
                TodoEntry.TODO_COLUMN};

        Cursor c = db.query(
                TodoEntry.TABLE_NAME,  // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // don't group the rows
                null,                  // don't filter by row groups
                null                   // The sort order
        );

        c.moveToFirst();
        while (!c.isAfterLast()){
            todoList.add(c.getString(1));
            c.moveToNext();
        }
        c.close();
        return todoList;
    }

}
