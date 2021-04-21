package com.project.manager.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="project_id", nullable = false)
    private String projectId;

    @Column(name="task_name", nullable = false)
    private String taskName;

    @Column(name="task_order")
    private String taskOrder;

    @Column(name="task_description")
    private String taskDescription;

    @Column(name="return_date_time", nullable = false)
    private String returnDateTime;

    public Task(String projectId, String taskName, String taskOrder, String taskDescription, String returnDateTime) {
        this.projectId = projectId;
        this.taskName = taskName;
        this.taskOrder = taskOrder;
        this.taskDescription = taskDescription;
        this.returnDateTime = returnDateTime;
    }
}
