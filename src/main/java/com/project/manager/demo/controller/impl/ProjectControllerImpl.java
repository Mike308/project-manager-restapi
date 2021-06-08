package com.project.manager.demo.controller.impl;

import com.project.manager.demo.controller.ProjectController;
import com.project.manager.demo.model.Project;
import com.project.manager.demo.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectControllerImpl implements ProjectController {
    private final ProjectService projectService;

    public ProjectControllerImpl(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @Override
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable long id, @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    @Override
    @DeleteMapping
    public void delete(long id) {
        projectService.deleteProject(id);
    }

    @Override
    @GetMapping("/{id}")
    public Project getProject(@PathVariable long id) {
        return projectService.getProject(id);
    }

    @Override
    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
}
