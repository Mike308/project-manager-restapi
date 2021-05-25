package com.project.manager.demo.controller;

import com.project.manager.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentController {
    Student addStudent(Student student);

    Student getStudentByIndexNumber(String indexNumber);

    Optional<Student> getStudentById(long id);

    Student updateStudent(long id, Student student);

    List<Student> getAllStudents();

    void deleteStudentById(long id);
}
