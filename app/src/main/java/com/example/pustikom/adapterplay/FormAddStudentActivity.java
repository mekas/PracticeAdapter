package com.example.pustikom.adapterplay;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.pustikom.adapterplay.com.example.pustikom.user.Student;
import com.example.pustikom.adapterplay.com.example.pustikom.user.StudentList;

/**
 * Created by pustikom on 12/10/16.
 */

public class FormAddStudentActivity extends AppCompatActivity {

    private EditText idText, nameText, noregText, mailText, phoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //register all View
        idText = (EditText) findViewById(R.id.edit_id);
        nameText = (EditText) findViewById(R.id.edit_nama);
        noregText = (EditText) findViewById(R.id.edit_nim);
        phoneText = (EditText) findViewById(R.id.edit_phone);
        mailText = (EditText) findViewById(R.id.edit_email);

        FloatingActionButton cancelButton = (FloatingActionButton) findViewById(R.id.cancelActionButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //close this activity
                finish();
            }
        });

        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.saveActionButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: implement action when store
                int id = Integer.parseInt(idText.getText().toString());
                String name = nameText.getText().toString();
                String noreg = noregText.getText().toString();
                String mail = mailText.getText().toString();
                String phone = phoneText.getText().toString();

                Student student = new Student(id,noreg,name,phone,mail);
                StudentList studentList = StudentList.getInstance();
                studentList.addStudent(student);
            }
        });
    }
}
