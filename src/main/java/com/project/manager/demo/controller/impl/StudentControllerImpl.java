package com.project.manager.demo.controller.impl;

import com.project.manager.demo.controller.StudentController;
import com.project.manager.demo.model.Student;
import com.project.manager.demo.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentControllerImpl implements StudentController {
    private final StudentService studentService;

    public StudentControllerImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @Override
    @GetMapping("indexNumber/{indexNumber}")
    public Student getStudentByIndexNumber(String indexNumber) {
        return studentService.getStudentByIndexNumber(indexNumber);
    }

    @Override
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @Override
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @Override
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable long id) {
        studentService.deleteStudentById(id);
    }

}
