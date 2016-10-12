package com.example.pustikom.adapterplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.pustikom.adapterplay.com.example.pustikom.adapter.StudentArrayAdapter;
import com.example.pustikom.adapterplay.com.example.pustikom.user.Student;
import com.example.pustikom.adapterplay.com.example.pustikom.user.StudentList;

import java.util.ArrayList;

/**
 * Created by pustikom on 07/10/16.
 */

public class StudentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        StudentList students = populateStudentDummies();
        StudentArrayAdapter studentArrayAdapter = new StudentArrayAdapter(this,students.getList());
        ListView list_item = (ListView) findViewById(R.id.list_item);
        list_item.setAdapter(studentArrayAdapter);

        //setlistener for floacting action bar
        FloatingActionButton addStudentButton = (FloatingActionButton)findViewById(R.id.addFloatingActionButton);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open Form Add Student
                Intent intent = new Intent(StudentActivity.this, FormAddStudentActivity.class);
                startActivity(intent);
            }
        });

    }

    private StudentList populateStudentDummies(){
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        studentArrayList.add(new Student("3145136188","TRI FEBRIANA SIAMI","0858xxxxxx","tri@mhs.unj.ac.id"));
        studentArrayList.add(new Student("3145136192","Ummu Kultsum","0813xxxxxx","ummu@mhs.unj.ac.id"));
        StudentList studentList = StudentList.getInstance();
        studentList.AddStudents(studentArrayList);
        return studentList;
    }

    /*
    private StudentList populateStudentDummies(){
        StudentList studentList = StudentList.getInstance();
        return null;
    }*/
}
