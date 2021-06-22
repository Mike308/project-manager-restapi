package com.project.manager.demo.controller;

import com.project.manager.demo.model.Project;

import java.util.List;

public interface ProjectController {
    Project addProject(Project project);
    Project updateProject(long id, Project project);
    void delete(long id);
    Project getProject(long id);
    List<Project> getAllProjects();
}
