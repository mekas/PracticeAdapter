package com.example.pustikom.adapterplay;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pustikom.adapterplay.db.StudentDbHelper;
import com.example.pustikom.adapterplay.db.StudentEntry;
import com.example.pustikom.adapterplay.user.Student;
import com.example.pustikom.adapterplay.user.StudentList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pustikom on 12/10/16.
 */

public class FormAddStudentActivity extends AppCompatActivity {

    private EditText nameText, noregText, mailText, phoneText;
    private Student student;
    private Spinner genderSpinner;
    private int mGender;
    private int act;
    private StudentDbHelper mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //register all View
        nameText = (EditText) findViewById(R.id.edit_nama);
        noregText = (EditText) findViewById(R.id.edit_nim);
        phoneText = (EditText) findViewById(R.id.edit_phone);
        mailText = (EditText) findViewById(R.id.edit_email);
        genderSpinner = (Spinner) findViewById(R.id.spinner_gender);

        mdb = new StudentDbHelper(this);

        //setup spinner
        setupSpinner();

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
            nameText.setText(student.getName());
            noregText.setText(student.getNoreg());
            phoneText.setText(student.getPhone());
            mailText.setText(student.getMail());
            genderSpinner.setSelection(student.getGender());
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
                String name = nameText.getText().toString();
                String noreg = noregText.getText().toString();
                String phone = phoneText.getText().toString();
                String mail = mailText.getText().toString();
                if(validate(noreg,name,phone,mail))
                    saveData(noreg, name, phone, mail);
            }
        });
    }

    private void setupSpinner(){
        ArrayAdapter genderSpinnerAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, StudentEntry.GENDERS);
        genderSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderSpinnerAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String gender = (String) adapterView.getItemAtPosition(position);
                switch (gender){
                    case StudentEntry.STRING_MALE:
                        mGender = StudentEntry.CODE_MALE;
                        break;
                    case StudentEntry.STRING_FEMALE:
                        mGender = StudentEntry.CODE_FEMALE;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
                mGender = StudentEntry.CODE_MALE;
            }
        });
    }

    private void saveData(String noreg,String name, String phone, String mail) {
        //StudentList studentList = StudentList.getInstance();
        SQLiteDatabase db = mdb.getWritableDatabase();
        switch (act) {
            case 0: //mode save
                student = new Student(noreg, name, phone, mail, mGender);
                //now we do not need to store to the database
                //studentList.addStudentWithIdTamper(student);
                mdb.insert(db,student);
                Toast.makeText(getApplicationContext(), "Student successfully added", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case 1: //mode edit
                long id=student.getId();
                student = new Student(id, noreg,name, phone, mail,mGender);
                //studentList.set((int) id,student);
                String condition = StudentEntry._ID + "=?";
                String[] conditionArg = {id + ""};
                int affectedRows =mdb.update(db,student,condition,conditionArg);
                Toast.makeText(getApplicationContext(),"Student successfully edited", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    public boolean validate(String noreg, String name, String phone, String mail){
        boolean isValidate=true;

        if(noreg.length()!=8){
            noregText.setError("NIM must be 8 digits");
            isValidate=false;
        }

        if(name.length()==0){
            nameText.setError("Please fill in your name");
            isValidate=false;
        }

        if(isValidEmail(mail) || mail.length()==0){
            mailText.setError("Please fill with proper email format");
            isValidate=false;
        }

        return isValidate;
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
                long id = student.getId();
                //delete from database
                String condition = StudentEntry._ID + "=?";
                String[] conditionArgs = {id+""};
                //we are not necessary anymore to delete from database
                mdb.delete(mdb.getWritableDatabase(),condition,conditionArgs);
                //StudentList.getInstance().remove((int) id);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
