package com.hfad.mylisttodo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * создание помощника SQLIteOpenHelper on 11.01.2017.
 */

 class ToDOListDatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "toDoList";
    private static  final  int DB_VERSION = 1;

    ToDOListDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE LIST (_id INTEGER PRIMARY AUTOINCREMENT,"
                        + "NAME TEXT");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
