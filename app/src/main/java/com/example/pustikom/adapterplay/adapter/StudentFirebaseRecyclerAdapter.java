package com.example.pustikom.adapterplay.adapter;

import com.example.pustikom.adapterplay.user.Student;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by eka on 19/12/16.
 */

public class StudentFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Student, StudentHolder> {
    public StudentFirebaseRecyclerAdapter(Class<Student> modelClass, int modelLayout, Class<StudentHolder> viewHolderClass, DatabaseReference ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(StudentHolder viewHolder, Student model, int position) {
        Student s=getItem(position);
        viewHolder.setStudent(s,position);
    }
}
