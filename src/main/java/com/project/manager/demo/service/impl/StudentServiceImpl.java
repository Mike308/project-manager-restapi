package com.project.manager.demo.service.impl;

import com.project.manager.demo.model.Student;
import com.project.manager.demo.repository.StudentRepository;
import com.project.manager.demo.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentByIndexNumber(String indexNumber) {
        return studentRepository.findByIndexNumber(indexNumber);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
}
