package com.example.pustikom.adapterplay;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pustikom.adapterplay.adapter.StudentArrayAdapter;
import com.example.pustikom.adapterplay.db.StudentDbHelper;
import com.example.pustikom.adapterplay.user.Student;
import com.example.pustikom.adapterplay.user.StudentList;

/**
 * Created by pustikom on 07/10/16.
 */

public class StudentActivity extends AppCompatActivity {
    private FloatingActionButton addButton;
    private StudentArrayAdapter studentArrayAdapter;
    private ListView listItem;
    private StudentDbHelper db;
    private StudentList studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        studentArrayAdapter = new StudentArrayAdapter(this,new StudentList());
        listItem = (ListView) findViewById(R.id.list_item);
        listItem.setAdapter(studentArrayAdapter);

        //register button
        addButton  = (FloatingActionButton) findViewById(R.id.floatingAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, StudentFormActivity.class);
                intent.putExtra("mode",0);
                startActivity(intent);
            }
        });

        db = new StudentDbHelper(getApplicationContext());
        SQLiteDatabase rdb =db.getReadableDatabase();
        studentList = db.fetchAllData();

        //set listener for each list item
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StudentActivity.this, StudentFormActivity.class);
                intent.putExtra("mode",1);
                Student student = studentList.get(position);
                intent.putExtra("Student",student);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        //after saving reload data from database
        SQLiteDatabase rdb =db.getReadableDatabase();
        studentList = db.fetchAllData();
        //call datasync to resynchronize the data
        new DataSyncTask().execute(studentList);
    }

    //store to database
    private StudentList populateStudentDummies(){
        StudentList studentList = new StudentList();
        SQLiteDatabase wdb =db.getWritableDatabase();
        Student s1=new Student("3145136188","TRI FEBRIANA SIAMI",1,"tri@mhs.unj.ac.id","0858xxxxxx");
        db.insert(s1);
        studentList.add(s1);

        Student s2=new Student("3145136192","Ummu Kultsum",1,"ummu@mhs.unj.ac.id","0813xxxxxx");
        db.insert(s2);
        studentList.add(s2);

        return studentList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        StudentList students;
        switch(item.getItemId()){
            case R.id.createDummyItem:
                students = populateStudentDummies();
                new DataSyncTask().execute(students);
                return true;
            case R.id.clearListItem:
                SQLiteDatabase wdb = db.getWritableDatabase();
                db.truncate();
                new DataSyncTask().execute(new StudentList());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DataSyncTask extends AsyncTask<StudentList, Void, StudentArrayAdapter>{
        @Override
        protected StudentArrayAdapter doInBackground(StudentList... params) {
            StudentArrayAdapter adapter = new StudentArrayAdapter(StudentActivity.this,params[0]);
            return adapter;
        }

        protected void onPostExecute(StudentArrayAdapter adapter){
            listItem = (ListView) findViewById(R.id.list_item);
            listItem.setAdapter(adapter);
        }
    }
}
