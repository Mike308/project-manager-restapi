package com.project.manager.demo.controller.impl;

import com.project.manager.demo.controller.StudentController;
import com.project.manager.demo.model.Student;
import com.project.manager.demo.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentControllerImpl implements StudentController {
    private final StudentService studentService;

    public StudentControllerImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    @PostMapping("/add")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
}
