package com.project.manager.demo.service.impl;

import com.project.manager.demo.model.Task;
import com.project.manager.demo.repository.TaskRepository;
import com.project.manager.demo.service.TaskService;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task getTaskByProjectId(String projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    @Override
    public Task updateTask(long id, Task task) {
        Task newTask = taskRepository.findById(id).get();
        newTask.setProjectId(task.getProjectId());
        newTask.setName(task.getName());
        newTask.setSequence(task.getSequence());
        newTask.setDescription(task.getDescription());
        newTask.setReturnDateTime(task.getReturnDateTime());
        return taskRepository.save(newTask);
    }
}
