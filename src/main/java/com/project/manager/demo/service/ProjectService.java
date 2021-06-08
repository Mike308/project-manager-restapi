package com.project.manager.demo.service;

import com.project.manager.demo.model.Project;

import java.util.List;

public interface ProjectService {
    Project addProject(Project project);
    Project updateProject(long id, Project project);
    void deleteProject(long id);
    Project getProject(long id);
    List<Project> getAllProjects();
}