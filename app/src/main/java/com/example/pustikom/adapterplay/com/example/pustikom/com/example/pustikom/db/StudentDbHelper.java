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

    /**
     * Called when database is created for the first time
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table " + StudentEntry.TABLE_NAME + "(" +
                StudentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                StudentEntry.COLUMN_NIM+ " TEXT NOT NULL, " +
                StudentEntry.COLUMN_NAME + " TEXT NOT NULL," +
                StudentEntry.COLUMN_GENDER + " INTEGER," +
                StudentEntry.COLUMN_MAIL + " TEXT," +
                StudentEntry.COLUMN_PHONE + " TEXT" +
                ");";
        db.execSQL(sql);
    }

    /**
     * Called when the database need to be upgraded
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
