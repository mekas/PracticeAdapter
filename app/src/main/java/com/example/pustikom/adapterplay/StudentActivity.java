package com.example.pustikom.adapterplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.pustikom.adapterplay.com.example.pustikom.adapter.StudentArrayAdapter;
import com.example.pustikom.adapterplay.com.example.pustikom.user.Student;

import java.util.ArrayList;

/**
 * Created by pustikom on 07/10/16.
 */

public class StudentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        ArrayList<Student> students = populateStudentDummies();
        StudentArrayAdapter studentArrayAdapter = new StudentArrayAdapter(this,students);
        ListView list_item = (ListView) findViewById(R.id.list_item);
        list_item.setAdapter(studentArrayAdapter);

    }

    private ArrayList<Student> populateStudentDummies(){
        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student(1,"3145136188","TRI FEBRIANA SIAMI","0858xxxxxx","tri@mhs.unj.ac.id"));
        studentList.add(new Student(2,"3145136192","Ummu Kultsum","0813xxxxxx","ummu@mhs.unj.ac.id"));
        return studentList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.createDummyItem:
                //TODO: add action
                return true;
            case R.id.clearListItem:
                //TODO: add action
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
