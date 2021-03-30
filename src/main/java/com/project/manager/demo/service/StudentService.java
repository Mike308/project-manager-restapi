package com.project.manager.demo.service;

import com.project.manager.demo.model.Student;

public interface StudentService {
    Student getStudentByIndexNumber(String indexNumber);
    Student addStudent(Student student);
}
