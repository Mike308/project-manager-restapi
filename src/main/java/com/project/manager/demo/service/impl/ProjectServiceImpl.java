package com.project.manager.demo.service.impl;

import com.project.manager.demo.model.Project;
import com.project.manager.demo.model.Student;
import com.project.manager.demo.repository.ProjectRepository;
import com.project.manager.demo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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
}
