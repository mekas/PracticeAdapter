package com.example.pustikom.adapterplay.com.example.pustikom.user;

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
}
