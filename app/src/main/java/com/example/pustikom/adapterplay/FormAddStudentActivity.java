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
        if(mode.compareTo("add")==0){
            setTitle("Add New Student");
        } else{
            setTitle("Edit Student");
            student = (Student) intent.getSerializableExtra("student_info");
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

                /*
                 * Store back to mainList
                 */
                int id = Integer.parseInt(idText.getText().toString());
                String name = nameText.getText().toString();
                String noreg = noregText.getText().toString();
                String mail = mailText.getText().toString();
                String phone = phoneText.getText().toString();

                Student student = new Student(id,noreg,name,phone,mail);
                StudentList studentList = StudentList.getInstance();
                studentList.addStudent(student);

                Toast.makeText(getApplicationContext(), "Data successfully added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    /**
     * Modify menu on runtime after invalidated
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);
        //TODO: if current page add don't show the menu at all
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.delete_item:
                //TODO: delete this item

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
