package com.example.pustikom.adapterplay.user;

import android.content.ContentValues;

import com.example.pustikom.adapterplay.db.StudentEntry;

import java.io.Serializable;

/**
 * Created by pustikom on 07/10/16.
 */

public class Student implements Serializable {
    private int id;
    private String noreg;
    private String name;
    private String phone;
    private String mail;
    private int gender;

    private static final int GENDER_MALE_CODE=0;
    private static final int GENDER_FEMALE_CODE=1;
    private static final String GENDER_MALE_TEXT="Laki-laki";
    private static final String GENDER_FEMALE_TEXT="Perempuan";

    public Student(String noreg, String name, int gender, String mail, String phone){
        this.noreg=noreg;
        this.name=name;
        this.gender=gender;
        this.mail=mail;
        this.phone=phone;
    }

    public Student(int id, String noreg, String name, int gender, String mail, String phone){
        this.id=id;
        this.noreg=noreg;
        this.name=name;
        this.gender=gender;
        this.mail=mail;
        this.phone=phone;
    }

    public String getNoreg() {
        return noreg;
    }

    public void setNoreg(String noreg) {
        this.noreg = noreg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public static ContentValues toContentValues(Student student){
        ContentValues values = new ContentValues();
        values.put(StudentEntry.COLUMN_NIM, student.getNoreg());
        values.put(StudentEntry.COLUMN_NAME, student.getName());
        values.put(StudentEntry.COLUMN_GENDER, student.getGender());
        values.put(StudentEntry.COLUMN_MAIL, student.getMail());
        values.put(StudentEntry.COLUMN_PHONE, student.getPhone());
        return values;
    }
}