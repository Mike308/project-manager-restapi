package com.project.manager.demo.service.impl;

import com.project.manager.demo.model.Project;
import com.project.manager.demo.model.Task;
import com.project.manager.demo.repository.ProjectRepository;
import com.project.manager.demo.repository.TaskRepository;
import com.project.manager.demo.service.TaskService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
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
    public List<Task> getTasksByProjectId(long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Nie znaleziono projektu"));
        return taskRepository.findAllByProjectId(project);
    }

    @Override
    public Task updateTask(long id, Task task) {
        Task newTask = taskRepository.findById(id).get();
        newTask.setName(task.getName());
        newTask.setSequence(task.getSequence());
        newTask.setDescription(task.getDescription());
        return taskRepository.save(newTask);
    }

    @Override
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteTaskByProjectId(Project projectId) {
        taskRepository.deleteByProjectId(projectId);
    }
}
