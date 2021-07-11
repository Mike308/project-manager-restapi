package com.project.manager.demo.repository;

import com.project.manager.demo.model.Project;
import com.project.manager.demo.model.ProjectFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectFileRepository extends JpaRepository<ProjectFile, Long> {
    void deleteByPath(String path);
    void deleteProjectFileByProject(Project project);
}
