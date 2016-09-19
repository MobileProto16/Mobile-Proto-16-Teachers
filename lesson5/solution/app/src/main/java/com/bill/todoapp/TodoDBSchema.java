package com.bill.todoapp;

/**
 * Created by shruti on 9/16/16.
 */

import android.provider.BaseColumns;

public final class TodoDBSchema {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private TodoDBSchema (){}

    public abstract class TodoEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String TODO_COLUMN= "todo";
        public static final String TODO_ID= "_id";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TodoEntry.TABLE_NAME + " (" +
                        TodoEntry.TODO_ID + " INTEGER PRIMARY KEY," +
                        TodoEntry.TODO_COLUMN + " TEXT )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TodoEntry.TABLE_NAME;
    }
}
