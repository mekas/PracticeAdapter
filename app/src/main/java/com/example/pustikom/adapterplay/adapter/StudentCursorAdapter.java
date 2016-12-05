package com.example.pustikom.adapterplay.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.pustikom.adapterplay.R;
import com.example.pustikom.adapterplay.db.StudentEntry;
import com.example.pustikom.adapterplay.user.Student;
import com.example.pustikom.adapterplay.user.StudentList;

/**
 * Created by Matematika on 05/12/2016.
 */

public class StudentCursorAdapter extends CursorAdapter {
    public StudentCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    /**
     * Inflate layout
     */
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.student_instance,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int idIndex = cursor.getColumnIndex(StudentEntry._ID);
        int nimIndex = cursor.getColumnIndex(StudentEntry.COLUMN_NIM);
        int nameIndex = cursor.getColumnIndex(StudentEntry.COLUMN_NAME);
        int genderIndex = cursor.getColumnIndex(StudentEntry.COLUMN_GENDER);
        int phoneIndex = cursor.getColumnIndex(StudentEntry.COLUMN_PHONE);
        int mailIndex = cursor.getColumnIndex(StudentEntry.COLUMN_MAIL);

        long id = cursor.getLong(idIndex);
        String nim = cursor.getString(nimIndex);
        String name = cursor.getString(nameIndex);
        int gender = cursor.getInt(genderIndex);
        String mail = cursor.getString(mailIndex);
        String phone = cursor.getString(phoneIndex);

        TextView idView = (TextView) view.findViewById(R.id.student_id);
        TextView noregView = (TextView) view.findViewById(R.id.student_noreg);
        TextView nameView = (TextView) view.findViewById(R.id.student_name);
        TextView genderView = (TextView) view.findViewById(R.id.student_gender);
        TextView mailView = (TextView) view.findViewById(R.id.student_email);
        TextView phoneView = (TextView) view.findViewById(R.id.student_phone);

        idView.setText("" + id);
        noregView.setText(nim);
        nameView.setText(name);
        switch (gender){
            case 0:
                genderView.setText("L");
                break;
            case 1:
                genderView.setText("P");
                break;
        }
        mailView.setText(mail);
        phoneView.setText(phone);
    }
}
