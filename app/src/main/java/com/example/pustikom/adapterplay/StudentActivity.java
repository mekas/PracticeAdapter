package com.example.pustikom.adapterplay;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pustikom.adapterplay.adapter.StudenFirebaseAdapter;
import com.example.pustikom.adapterplay.adapter.StudentCursorAdapter;
import com.example.pustikom.adapterplay.user.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by pustikom on 07/10/16.
 */

public class StudentActivity extends AppCompatActivity {
    private FloatingActionButton addButton;
    private StudenFirebaseAdapter firebaseAdapter;
    private ListView listItem;
    private DatabaseReference mFirebaseDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

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

        //setup firebase
        mFirebaseDb = FirebaseDatabase.getInstance().getReference();
        firebaseAdapter=new StudenFirebaseAdapter(StudentActivity.this,Student.class,R.layout.student_instance,mFirebaseDb.child("student"));
        listItem = (ListView) findViewById(R.id.list_item);
        listItem.setAdapter(firebaseAdapter);

        //do something when data has changed
        mFirebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String key = child.getKey();
                    Log.w("Debug", key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //set listener for each list item
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StudentActivity.this, StudentFormActivity.class);
                intent.putExtra("mode",1);

                Student student = firebaseAdapter.getItem(position);
                intent.putExtra("Student",student);
                startActivity(intent);
            }
        });
    }

    /*@Override
    public void onResume(){
        super.onResume();
        //after saving reload data from database
        //call datasync to resynchronize the data
        //new DataSyncTask().execute(db.getCursor());
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        firebaseAdapter.cleanup();
    }

    /**
     * Write to SQLite and Firebase
     */
    private void populateStudentDummies(){
        Student s1=new Student("3145136188","TRI FEBRIANA SIAMI",1,"tri@mhs.unj.ac.id","0858xxxxxx");
        Student s2=new Student("3145136192","Ummu Kultsum",1,"ummu@mhs.unj.ac.id","0813xxxxxx");

        //test case
        mFirebaseDb.child("student").push().setValue(s1);
        mFirebaseDb.child("student").push().setValue(s2);
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
                populateStudentDummies();
                return true;
            case R.id.clearListItem:
                mFirebaseDb.child("student").removeValue();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Unused due to Firebase is adaptive to Data Change
     */
    private class DataSyncTask extends AsyncTask<Cursor, Void, StudentCursorAdapter>{
        @Override
        protected StudentCursorAdapter doInBackground(Cursor... params) {
            StudentCursorAdapter adapter = new StudentCursorAdapter(StudentActivity.this,params[0]);
            return adapter;
        }

        protected void onPostExecute(StudentCursorAdapter adapter){
            listItem = (ListView) findViewById(R.id.list_item);
            listItem.setAdapter(adapter);
        }
    }
}
