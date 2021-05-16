package com.project.manager.demo.repository;

import com.project.manager.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByIndexNumber(String indexNumber);
}
