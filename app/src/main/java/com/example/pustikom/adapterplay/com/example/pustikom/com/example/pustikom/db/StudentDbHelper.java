package com.example.pustikom.adapterplay.com.example.pustikom.com.example.pustikom.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pustikom on 20/10/16.
 */

public class StudentDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG=StudentDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME="college.db";
    private static final int DATABASE_VERSION=1;

    public StudentDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
