package com.example.pustikom.adapterplay;


import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pustikom.adapterplay.adapter.StudentArrayAdapter;
import com.example.pustikom.adapterplay.db.StudentDbHelper;
import com.example.pustikom.adapterplay.db.StudentEntry;
import com.example.pustikom.adapterplay.user.Student;
import com.example.pustikom.adapterplay.user.StudentList;

import java.util.ArrayList;

/**
 * Created by pustikom on 07/10/16.
 *
 */

public class StudentActivity extends AppCompatActivity {

    private StudentArrayAdapter studentArrayAdapter;
    private StudentList studentList;
    private ListView studentListView;
    private StudentDbHelper mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        studentList = StudentList.getInstance();


        //setlistener for floacting action bar
        FloatingActionButton addStudentButton = (FloatingActionButton)findViewById(R.id.addFloatingActionButton);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open Form Add Student
                Intent intent = new Intent(StudentActivity.this, FormAddStudentActivity.class);
                intent.putExtra("action","add");
                startActivity(intent);
            }
        });

        studentListView = (ListView) findViewById(R.id.list_item);
        //set up on item click handler
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(StudentActivity.this, FormAddStudentActivity.class);
                //we want to pass two data: action mode, and student info
                intent.putExtra("action","edit");
                Student student = studentList.get(position);
                intent.putExtra("student_info",student);
                startActivity(intent);
            }
        });

        //instantiate reference to database
        mdb = new StudentDbHelper(this);

    }

    /**
     * Read each student from database then stored to list
     */
    private void loadStudentFromDB(){
        SQLiteDatabase rdb = mdb.getReadableDatabase();
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

        //always reset list before load fresh data from database
        studentList.clearList();
        while(cursor.moveToNext()){
            long id = cursor.getLong(idIndex);
            String nim = cursor.getString(nimIndex);
            String name = cursor.getString(nameIndex);
            int gender = cursor.getInt(genderIndex);
            String mail = cursor.getString(mailIndex);
            String phone = cursor.getString(phoneIndex);
            //create student instance based on these data
            Student student = new Student(id,nim,name,phone,mail,gender);

            studentList.addStudent(student);
        }

        cursor.close();
    }

    @Override
    public void onResume(){
        super.onResume();
        //immedietely load list from database
        loadStudentFromDB();
        //prepare emptyView
        TextView mEmptyTextView =(TextView) findViewById(R.id.empty_view);
        studentListView.setEmptyView(mEmptyTextView);
        //get studentList from latest cache
        studentList=StudentList.getInstance();
        //check size of student list
        if(studentList.count()==0) {
            studentArrayAdapter = new StudentArrayAdapter(this, new ArrayList<Student>());
            //set emptyView
            mEmptyTextView.setText("No Student Found");
        } else{
            studentArrayAdapter = new StudentArrayAdapter(this, studentList.getList());
        }
        studentListView.setAdapter(studentArrayAdapter);
    }

    /**
     * Generate data, store to list, then store to sqliteDb
     */
    private void populateStudentDummies(){
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        studentArrayList.add(new Student("3145136188","TRI FEBRIANA SIAMI","0858xxxxxx","tri@mhs.unj.ac.id", StudentEntry.CODE_FEMALE));
        studentArrayList.add(new Student("3145136192","Ummu Kultsum","0813xxxxxx","ummu@mhs.unj.ac.id", StudentEntry.CODE_FEMALE));

        insertToDbFromList(studentArrayList);
        //post inserting to database then set the adapter, don't mistaken on the order
        studentList.AddStudents(studentArrayList);
        studentArrayAdapter = new StudentArrayAdapter(this,studentList.getList());
        studentListView.setAdapter(studentArrayAdapter);
    }

    /**
     * Insert list data to database
     * @param students
     */
    private void insertToDbFromList(ArrayList<Student> students){
        SQLiteDatabase db = mdb.getWritableDatabase();
        for (Student student: students
             ) {
            mdb.insert(db,student);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_student_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.create_dummy:
                populateStudentDummies();
                return true;
            case R.id.clear_list:
                //clear list
                StudentList.getInstance().clearList();
                //reset adapter
                studentArrayAdapter = new StudentArrayAdapter(this, new ArrayList<Student>());
                studentListView.setAdapter(studentArrayAdapter);
                //clear student table
                SQLiteDatabase wdb = mdb.getWritableDatabase();
                mdb.truncate(wdb);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
