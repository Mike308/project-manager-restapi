package com.project.manager.demo.repository;

import com.project.manager.demo.model.Project;
import com.project.manager.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByStudentsIn(List<Student> students);
}
