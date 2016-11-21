package com.example.pustikom.adapterplay.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pustikom.adapterplay.user.Student;

/**
 * Created by pustikom on 21/11/16.
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

    /**
     * Insert this student instance to table Student, then update id on student
     * @param wdb
     * @param student
     */
    public void insert(SQLiteDatabase wdb, Student student){
        ContentValues values = Student.toContentValues(student);
        long rowId = wdb.insert(StudentEntry.TABLE_NAME, null, values);
    }

    public int update(SQLiteDatabase wdb, Student student){
        ContentValues values = Student.toContentValues(student);
        String condition = StudentEntry._ID + "=?";
        String[] conditionArg = {student.getId() + ""};
        int affectedRows = wdb.update(StudentEntry.TABLE_NAME,values,condition,conditionArg);
        return affectedRows;
    }

    /**
     * Truncate rows from table student
     * @param db
     */
    public void truncate(SQLiteDatabase db){
        String sql = "DELETE FROM " + StudentEntry.TABLE_NAME + ";VACUUM;";
        db.execSQL(sql);
    }

    /**
     * Delete certain row based on condition
     * @param db
     * @param condition
     * @param conditionArg
     * @return
     */
    public int delete(SQLiteDatabase db, String condition, String[] conditionArg){
        int affectedRows = db.delete(StudentEntry.TABLE_NAME,condition, conditionArg);
        return affectedRows;
    }
}