package com.project.manager.demo.service.impl;

import com.project.manager.demo.model.Project;
import com.project.manager.demo.model.Student;
import com.project.manager.demo.model.Task;
import com.project.manager.demo.repository.ProjectRepository;
import com.project.manager.demo.repository.TaskRepository;
import com.project.manager.demo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(long id, Project project) {
        return projectRepository.save(new Project(id, project.getName(), project.getDescription(),project.getReturnDate()));
    }

    @Override
    public void deleteProject(long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono projektu"));
        taskRepository.deleteByProjectId(project);
        projectRepository.deleteById(id);
    }

    @Override
    public Project getProject(long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono projektu"));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project assignStudentToProject(long id, Student student) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono projektu"));
        List<Student> students = project.getStudents();
        if (students.stream().anyMatch(studentInSet -> studentInSet.equals(student)))
            throw new RuntimeException("Student jest już przypisany do projektu");
        students.add(student);
        project.setStudents(students);
        return projectRepository.save(project);
    }

    @Override
    public Project assignTaskToProject(long id, Task task) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono projektu"));
        task.setProjectId(project);
        Task taskToSave = taskRepository.save(task);
        System.out.println("Project: " + taskToSave.getProjectId());
        return taskToSave.getProjectId();
    }
}
