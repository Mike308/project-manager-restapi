package com.project.manager.demo.service;

import com.project.manager.demo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task addTask(Task task);
    Optional<Task> getTaskById(long id);
    Task getTaskByProjectId(String projectId);
    Task updateTask(long id, Task task);
    List<Task> getAllTasks();
    void deleteTaskById(long id);
    void deleteTaskByProjectId(String projectId);
}
