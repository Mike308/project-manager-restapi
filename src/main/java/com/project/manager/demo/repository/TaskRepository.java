package com.project.manager.demo.repository;

import com.project.manager.demo.model.Project;
import com.project.manager.demo.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findByProjectId(Project projectId);
    void deleteById(long id);
    void deleteByProjectId(Project projectId);
}
