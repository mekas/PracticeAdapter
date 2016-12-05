package com.example.pustikom.adapterplay.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pustikom.adapterplay.user.Student;
import com.example.pustikom.adapterplay.user.StudentList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pustikom on 21/11/16.
 */

public class StudentDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG=StudentDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME="college2.db";
    private static final int DATABASE_VERSION=1;
    private SQLiteDatabase rdb, wdb;

    public StudentDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        rdb=this.getReadableDatabase();
        wdb=this.getWritableDatabase();
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
     * @param student
     */
    public void insert(Student student){
        ContentValues values = Student.toContentValues(student);
        long rowId = wdb.insert(StudentEntry.TABLE_NAME, null, values);
        student.setId((int)rowId);
    }

    public int update(Student student){
        ContentValues values = Student.toContentValues(student);
        String condition = StudentEntry._ID + "=?";
        String[] conditionArg = {student.getId() + ""};
        int affectedRows = wdb.update(StudentEntry.TABLE_NAME,values,condition,conditionArg);

        return affectedRows;
    }

    public Cursor getCursor(){
        //define columns to project
        String[] projection = {
                StudentEntry._ID, StudentEntry.COLUMN_NIM, StudentEntry.COLUMN_NAME, StudentEntry.COLUMN_GENDER,
                StudentEntry.COLUMN_MAIL, StudentEntry.COLUMN_PHONE
        };
        Cursor cursor = rdb.query(
                StudentEntry.TABLE_NAME, //table to query
                projection, //columns to project
                null, //column for the where clause
                null, //value for the where clause
                null, //group statement
                null, //don't filter by row groups
                null); //order by statemnt
        return cursor;
    }

    public Student constructStudent(Cursor cursor, int position){
        int idIndex = cursor.getColumnIndex(StudentEntry._ID);
        int nimIndex = cursor.getColumnIndex(StudentEntry.COLUMN_NIM);
        int nameIndex = cursor.getColumnIndex(StudentEntry.COLUMN_NAME);
        int genderIndex = cursor.getColumnIndex(StudentEntry.COLUMN_GENDER);
        int phoneIndex = cursor.getColumnIndex(StudentEntry.COLUMN_PHONE);
        int mailIndex = cursor.getColumnIndex(StudentEntry.COLUMN_MAIL);

        cursor.moveToPosition(position);
        long id = cursor.getLong(idIndex);
        String nim = cursor.getString(nimIndex);
        String name = cursor.getString(nameIndex);
        int gender = cursor.getInt(genderIndex);
        String mail = cursor.getString(mailIndex);
        String phone = cursor.getString(phoneIndex);
        //create student instance based on these data
        Student student = new Student((int)id, nim,name,gender,mail,phone);
        return student;
    }

    public StudentList fetchAllData(){
        //define columns to project
        String[] projection = {
                StudentEntry._ID, StudentEntry.COLUMN_NIM, StudentEntry.COLUMN_NAME, StudentEntry.COLUMN_GENDER,
                StudentEntry.COLUMN_MAIL, StudentEntry.COLUMN_PHONE
        };
        Cursor cursor = rdb.query(
                StudentEntry.TABLE_NAME, //table to query
                projection, //columns to project
                null, //column for the where clause
                null, //value for the where clause
                null, //group statement
                null, //don't filter by row groups
                null); //order by statemnt

        //figure out column index given id
        int idIndex = cursor.getColumnIndex(StudentEntry._ID);
        int nimIndex = cursor.getColumnIndex(StudentEntry.COLUMN_NIM);
        int nameIndex = cursor.getColumnIndex(StudentEntry.COLUMN_NAME);
        int genderIndex = cursor.getColumnIndex(StudentEntry.COLUMN_GENDER);
        int phoneIndex = cursor.getColumnIndex(StudentEntry.COLUMN_PHONE);
        int mailIndex = cursor.getColumnIndex(StudentEntry.COLUMN_MAIL);

        StudentList studentList = new StudentList();
        while(cursor.moveToNext()){
            long id = cursor.getLong(idIndex);
            String nim = cursor.getString(nimIndex);
            String name = cursor.getString(nameIndex);
            int gender = cursor.getInt(genderIndex);
            String mail = cursor.getString(mailIndex);
            String phone = cursor.getString(phoneIndex);
            //create student instance based on these data
            Student student = new Student((int)id, nim,name,gender,mail,phone);

            studentList.add(student);
        }

        cursor.close();
        return studentList;
    }

    /**
     * Truncate rows from table student
     */
    public void truncate(){
        String sql = "DELETE FROM " + StudentEntry.TABLE_NAME + ";VACUUM;";
        wdb.execSQL(sql);
    }

    /**
     * Delete certain row based on condition
     * @param id, int
     * @return
     */
    public int delete(int id){
        String condition = StudentEntry._ID + "=?";
        String[] conditionArg = {id + ""};
        int affectedRows = wdb.delete(StudentEntry.TABLE_NAME,condition, conditionArg);
        return affectedRows;
    }
}