package com.example.pustikom.adapterplay.user;

import android.content.ContentValues;

import com.example.pustikom.adapterplay.db.StudentEntry;

import java.io.Serializable;

/**
 * Created by pustikom on 07/10/16.
 */

public class Student implements Serializable {
    private long id;
    private String noreg;
    private String name;
    private String phone;
    private String mail;
    private int gender;

    public Student(String noreg, String name, String phone, String mail){
        this.id = 0;
        this.noreg = noreg;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
    }

    public Student(String noreg, String name, String phone, String mail, int gender){
        this.id = 0;
        this.noreg = noreg;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.gender = gender;
    }

    public Student(long id, String noreg, String name, String phone, String mail){
        this.id=id;
        this.noreg=noreg;
        this.name=name;
        this.phone=phone;
        this.mail=mail;
    }

    public Student(long id, String noreg, String name, String phone, String mail, int gender){
        this.id=id;
        this.noreg=noreg;
        this.name=name;
        this.phone=phone;
        this.mail=mail;
        this.gender = gender;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
