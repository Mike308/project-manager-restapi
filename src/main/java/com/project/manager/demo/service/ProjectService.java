package com.project.manager.demo.service;

import com.project.manager.demo.model.Project;
import com.project.manager.demo.model.Student;
import com.project.manager.demo.model.Task;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {
    Project addProject(Project project);
    Project updateProject(long id, Project project);
    Project setReturnDate(long id);
    void deleteProject(long id);
    Project getProject(long id);
    List<Project> getAllProjects();
    List<Project> getAllProjectsByStudentIndexNumber(String indexNumber);
    Project assignStudentToProject(long id, Student student);
    Project assignTaskToProject(long id, Task task);
    ResponseEntity<Object> addFileToProject(long id, MultipartFile multipartFile);
    ResponseEntity<InputStreamResource> downloadFileFromProject(long id, String path);
    ResponseEntity<Object> removeFileFromProject(long id, String path);
    ResponseEntity<Object> removeStudentFromProject(long projectId, long studentId);
}
