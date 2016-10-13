package com.example.pustikom.adapterplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pustikom.adapterplay.com.example.pustikom.user.Student;
import com.example.pustikom.adapterplay.com.example.pustikom.user.StudentList;

import java.io.Serializable;

/**
 * Created by pustikom on 12/10/16.
 */

public class FormAddStudentActivity extends AppCompatActivity {

    private EditText idText, nameText, noregText, mailText, phoneText;
    private Student student;
    private int act;

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

        //get Intent
        Intent intent = getIntent();
        String mode = intent.getStringExtra("action");
        if (mode.compareTo("add") == 0) {
            act=0;
            setTitle("Add New Student");
            invalidateOptionsMenu();
        } else {
            act=1;
            setTitle("Edit Student");
            student = (Student) intent.getSerializableExtra("student_info");
            idText.setText("" + student.getId());
            nameText.setText(student.getName());
            noregText.setText(student.getNoreg());
            phoneText.setText(student.getPhone());
            mailText.setText(student.getMail());
        }

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
                saveData();
            }
        });
    }

    private void saveData() {
        int id = Integer.parseInt(idText.getText().toString());
        String name = nameText.getText().toString();
        String noreg = noregText.getText().toString();
        String mail = mailText.getText().toString();
        String phone = phoneText.getText().toString();
        student = new Student(id, noreg, name, phone, mail);
        StudentList studentList = StudentList.getInstance();
        switch (act) {
            case 0: //mode save
                studentList.addStudent(student);
                Toast.makeText(getApplicationContext(), "Student successfully added", Toast.LENGTH_SHORT).show();
                finish();
            case 1: //mode edit
                studentList.set(id,student);
                Toast.makeText(getApplicationContext(),"Student successfully edited", Toast.LENGTH_SHORT).show();
                finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    /**
     * Modify menu on runtime after invalidated
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if(act==0){
            MenuItem item = menu.findItem(R.id.delete_item);
            item.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_item:
                //get current id
                int id = student.getId();
                StudentList.getInstance().remove(id);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
