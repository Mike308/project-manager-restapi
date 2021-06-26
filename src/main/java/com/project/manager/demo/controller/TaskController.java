package com.project.manager.demo.controller;

import com.project.manager.demo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskController {
    Task addTask(Task task);
    List<Task> getAllTasks();
    Optional<Task> getTaskById(long id);
    Task getTaskByProjectId(long projectId);
    Task updateTask(long id, Task task);
    void deleteTaskById(long id);
}
