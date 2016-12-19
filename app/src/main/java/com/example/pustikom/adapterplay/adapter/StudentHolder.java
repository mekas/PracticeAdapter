package com.example.pustikom.adapterplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pustikom.adapterplay.R;
import com.example.pustikom.adapterplay.StudentActivity;
import com.example.pustikom.adapterplay.StudentFormActivity;
import com.example.pustikom.adapterplay.user.Student;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.concurrent.RecursiveAction;

/**
 * Created by eka on 19/12/16.
 */

public class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final TextView idView, noregView, nameView, genderView, mailView, phoneView;
    private final Context context;
    private Student student;

    public StudentHolder(View v) {
        super(v);
        context=v.getContext();
        this.idView = (TextView) v.findViewById(R.id.student_id);
        this.noregView = (TextView) v.findViewById(R.id.student_noreg);
        this.nameView = (TextView) v.findViewById(R.id.student_name);
        this.genderView = (TextView) v.findViewById(R.id.student_gender);
        this.mailView = (TextView) v.findViewById(R.id.student_email);
        this.phoneView = (TextView) v.findViewById(R.id.student_phone);
        v.setOnClickListener(this);
    }

    private void setId(String id){
        idView.setText(id);
    }

    private void setNim(String nim){
        noregView.setText(nim);
    }

    private void setName(String name){
        nameView.setText(name);
    }

    private void setGender(int id){
        switch (id){
            case 0:
                genderView.setText("L");
                break;
            case 1:
                genderView.setText("P");
                break;
        }
    }

    private void setMail(String mail){
        mailView.setText(mail);
    }

    private void setPhone(String phone){
        phoneView.setText(phone);
    }

    public void setStudent(Student student, int position){
        this.student=student;
        setId(position+1+"");
        setNim(student.getNoreg());
        setName(student.getName());
        setPhone(student.getPhone());
        setMail(student.getMail());
        setGender(student.getGender());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, StudentFormActivity.class);
        intent.putExtra("mode",1);

        intent.putExtra("Student",this.student);
        context.startActivity(intent);
    }
}
