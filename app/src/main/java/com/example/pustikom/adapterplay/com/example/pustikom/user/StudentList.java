package com.example.pustikom.adapterplay.com.example.pustikom.user;

import java.util.ArrayList;

/**
 * Created by pustikom on 12/10/16.
 */

public class StudentList {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static StudentList instance = new StudentList();
    private static int id=0;

    //define singleton class
    private StudentList(){
        //studentList = new ArrayList<>();
        //instance = new StudentList();
        //id=1;
    }

    public static StudentList getInstance(){
        return instance;
    }

    public void addStudent(Student student){
        student.setId(nextId());
        studentList.add(student);
    }

    public int nextId(){
        id++;
        return id;
    }

    public Student removeLast(){
        Student student = studentList.remove(studentList.size()-1);
        return student;
    }

    public Student getLast(){
        Student student = studentList.get(studentList.size()-1);
        return student;
    }

    public void AddStudents(ArrayList<Student> students){
        studentList.addAll(students);
    }

    public ArrayList<Student> getList(){
        return studentList;
    }

    public int count(){
        return studentList.size();
    }
}
