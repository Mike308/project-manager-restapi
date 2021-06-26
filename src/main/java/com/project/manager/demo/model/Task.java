package com.project.manager.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
//    @JsonIgnore
    @JsonBackReference
    private Project projectId;

    @Column(nullable = false)
    private String name;

    @Column
    private String sequence;

    @Column
    private String description;

    @Column(name = "insert_date_time")
    @CreationTimestamp
    private LocalDateTime insertDateTime;


    public Task(Project projectId, String name, String sequence, String description, LocalDateTime insertDateTime) {
        this.projectId = projectId;
        this.name = name;
        this.sequence = sequence;
        this.description = description;
        this.insertDateTime = insertDateTime;
    }
}
