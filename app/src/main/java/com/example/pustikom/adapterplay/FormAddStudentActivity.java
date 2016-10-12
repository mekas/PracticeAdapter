package com.example.pustikom.adapterplay;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by pustikom on 12/10/16.
 */

public class FormAddStudentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

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
            }
        });
    }
}
