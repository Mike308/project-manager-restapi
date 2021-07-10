package com.project.manager.demo.controller.impl;

import com.project.manager.demo.controller.ProjectController;
import com.project.manager.demo.model.Project;
import com.project.manager.demo.model.Student;
import com.project.manager.demo.model.Task;
import com.project.manager.demo.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
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

    @Override
    @GetMapping("/all/{indexNumber}")
    public List<Project> getAllProjectsByIndexNumber(@PathVariable String indexNumber) {
        return projectService.getAllProjectsByStudentIndexNumber(indexNumber);
    }

    @Override
    @PutMapping("/assign-student/{id}")
    public Project assignStudentToProject(@PathVariable long id, @RequestBody Student student) {
        return projectService.assignStudentToProject(id, student);
    }

    @Override
    @PutMapping("/assign-task/{id}")
    public Project assignTaskToProject(@PathVariable long id, @RequestBody Task task) {
        return projectService.assignTaskToProject(id, task);
    }

    @Override
    @PostMapping("/upload-file/{id}")
    public ResponseEntity<Object> addFileToProject(@PathVariable long id, @RequestParam("file") MultipartFile multipartFile) {
        return projectService.addFileToProject(id, multipartFile);
    }

    @Override
    @DeleteMapping("/{id}/{path}")
    public ResponseEntity<Object> removeFileFromProject(@PathVariable long id, @PathVariable String path) {
        return projectService.removeFileFromProject(id, path);
    }
}
