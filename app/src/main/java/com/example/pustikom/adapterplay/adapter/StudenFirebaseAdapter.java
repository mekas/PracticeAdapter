package com.example.pustikom.adapterplay.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.pustikom.adapterplay.R;
import com.example.pustikom.adapterplay.user.Student;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by eka on 06/12/16.
 */

public class StudenFirebaseAdapter extends FirebaseListAdapter<Student> {
    public StudenFirebaseAdapter(Activity activity, Class<Student> modelClass, int modelLayout, DatabaseReference ref) {
        super(activity, modelClass, modelLayout, ref);
    }

    @Override
    protected void populateView(View v, Student student, int position) {
        TextView idView = (TextView) v.findViewById(R.id.student_id);
        TextView noregView = (TextView) v.findViewById(R.id.student_noreg);
        TextView nameView = (TextView) v.findViewById(R.id.student_name);
        TextView genderView = (TextView) v.findViewById(R.id.student_gender);
        TextView mailView = (TextView) v.findViewById(R.id.student_email);
        TextView phoneView = (TextView) v.findViewById(R.id.student_phone);
        idView.setText(position+1 + "");
        noregView.setText(student.getNoreg());
        nameView.setText(student.getName());
        switch (student.getGender()){
            case 0:
                genderView.setText("L");
                break;
            case 1:
                genderView.setText("P");
                break;
        }
        mailView.setText(student.getMail());
        phoneView.setText(student.getPhone());
    }
}
