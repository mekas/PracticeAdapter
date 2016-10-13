package com.example.pustikom.adapterplay.com.example.pustikom.user;

import java.util.ArrayList;

/**
 * Created by pustikom on 12/10/16.
 */

public class StudentList {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static StudentList instance = new StudentList();

    //set singleton class
    private StudentList(){

    }

    public static StudentList getInstance(){
        return instance;
    }

    public void addStudent(Student student){
        student.setId(nextId());
        studentList.add(student);
    }

    public int nextId(){
        return studentList.size()+1;
    }

    public Student removeLast(){
        Student student = studentList.remove(studentList.size()-1);
        return student;
    }

    public Student get(int index){
        Student student = studentList.get(index);
        return student;
    }

    public Student remove(int index){
        Student student = studentList.remove(index);
        resetCounterId(index);
        return student;
    }

    public Student getLast(){
        Student student = studentList.get(studentList.size()-1);
        return student;
    }

    public void AddStudents(ArrayList<Student> students){
        studentList.addAll(students);
        resetCounterId(0);
    }

    public ArrayList<Student> getList(){
        return studentList;
    }

    public int count(){
        return studentList.size();
    }

    private void resetCounterId(int i){
        for (int j = i; j < studentList.size(); j++) {
            studentList.get(j).setId(j);
        }
    }
}
