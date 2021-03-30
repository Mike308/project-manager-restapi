package com.project.manager.demo.repository;

import com.project.manager.demo.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByIndexNumber(String indexNumber);
}
