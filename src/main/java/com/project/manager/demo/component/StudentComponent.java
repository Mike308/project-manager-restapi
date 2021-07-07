package com.project.manager.demo.component;

import com.project.manager.demo.model.Student;
import com.project.manager.demo.service.StudentService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StudentComponent {
    private final StudentService studentService;

    public StudentComponent(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostConstruct
    private void insertTestStudents() {
        studentService.addStudent(new Student("Jan", "Kowalski", "99536", "jankow001@utp.edu.pl", true));
        studentService.addStudent(new Student("Arkadiusz", "Piątek", "100234", "arkpia001@utp.edu.pl", true));
    }
}
