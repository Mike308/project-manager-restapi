package com.project.manager.demo.repository;

import com.project.manager.demo.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findByProjectId(String projectId);
}
