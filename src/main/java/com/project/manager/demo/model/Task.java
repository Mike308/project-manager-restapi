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

    @Column(name = "project_id", nullable = false)
    private String projectId;

    @Column(nullable = false)
    private String name;

    @Column
    private String sequence;

    @Column
    private String description;

    @Column(name = "return_date_time", nullable = false)
    private String returnDateTime;

    public Task(String projectId, String name, String sequence, String description, String returnDateTime) {
        this.projectId = projectId;
        this.name = name;
        this.sequence = sequence;
        this.description = description;
        this.returnDateTime = returnDateTime;
    }
}
