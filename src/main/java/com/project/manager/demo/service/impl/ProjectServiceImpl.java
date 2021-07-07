package com.project.manager.demo.service.impl;

import com.project.manager.demo.model.Project;
import com.project.manager.demo.model.ProjectFile;
import com.project.manager.demo.model.Student;
import com.project.manager.demo.model.Task;
import com.project.manager.demo.repository.ProjectFileRepository;
import com.project.manager.demo.repository.ProjectRepository;
import com.project.manager.demo.repository.TaskRepository;
import com.project.manager.demo.service.ProjectService;
import com.project.manager.demo.validator.ProjectAndTaskPermissionValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ProjectFileRepository projectFileRepository;
    private final ProjectAndTaskPermissionValidator projectAndTaskPermissionValidator;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository, ProjectFileRepository projectFileRepository, ProjectAndTaskPermissionValidator projectAndTaskPermissionValidator) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.projectFileRepository = projectFileRepository;
        this.projectAndTaskPermissionValidator = projectAndTaskPermissionValidator;
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
//                .findAll().parallelStream()
//                .filter(projectAndTaskPermissionValidator::limitProjectsList)
//                .collect(Collectors.toList());
    }

    @Override
    public List<Project> getAllProjectsByStudentIndexNumber(String indexNumber) {
        return projectRepository.findAll()
                .parallelStream().filter(project -> project.getStudents() != null)
                .filter(project -> project.getStudents().parallelStream()
                        .filter(student -> student.getIndexNumber() != null)
                        .anyMatch(student -> student.getIndexNumber().equals(indexNumber)))
                .collect(Collectors.toList());
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
        return taskToSave.getProjectId();
    }

    @Override
    public ResponseEntity<Object> addFileToProject(long id, MultipartFile multipartFile) {
        Project project = getProject(id);
        try {
            File projectFileDir = new File(File.pathSeparator + id);
            projectFileDir.mkdir();
            Path copyLocation = Paths.get(id + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
            Files.copy(multipartFile.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            ProjectFile projectFile = new ProjectFile(multipartFile.toString(), project);
            projectFileRepository.save(projectFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }
}
