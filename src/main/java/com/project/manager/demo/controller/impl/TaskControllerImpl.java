package com.project.manager.demo.controller.impl;

import com.project.manager.demo.controller.TaskController;
import com.project.manager.demo.model.Task;
import com.project.manager.demo.service.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class TaskControllerImpl implements TaskController {
    private final TaskService taskService;

    public TaskControllerImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @Override
    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Override
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @Override
    @GetMapping("/projectId{projectId}")
    public Task getTaskByProjectId(@PathVariable long projectId) {
        return taskService.getTaskByProjectId(projectId);
    }

    @Override
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody Task task) {
        return taskService.updateTask(id,task);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable long id) {
        taskService.deleteTaskById(id);
    }

}
