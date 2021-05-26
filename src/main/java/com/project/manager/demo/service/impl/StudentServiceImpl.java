package com.project.manager.demo.service.impl;

import com.project.manager.demo.model.Student;
import com.project.manager.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.project.manager.demo.service.StudentService;

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

    @Override
    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(long id, Student student) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new RuntimeException("Student with id={} does not exist in our database!");
        }
        Student newStudent = new Student(id, student.getFirstName(), student.getSurname(), student.getIndexNumber(), student.getEmail(), student.isFullTimeStudent());
        return studentRepository.save(newStudent);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }
}
