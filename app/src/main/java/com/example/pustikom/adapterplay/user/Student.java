package com.example.pustikom.adapterplay.user;

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

    public Student(String noreg, String name, String phone, String mail){
        this.id = 0;
        this.noreg = noreg;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
    }

    public Student(int id, String noreg, String name, String phone, String mail){
        this.id=id;
        this.noreg=noreg;
        this.name=name;
        this.phone=phone;
        this.mail=mail;
    }

    public Student(int id, String noreg, String name, String phone, String mail, int gender){
        this.id=id;
        this.noreg=noreg;
        this.name=name;
        this.phone=phone;
        this.mail=mail;
        this.setGender(gender);
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
}
