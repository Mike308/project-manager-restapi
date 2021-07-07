package com.project.manager.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFile {
    @Id
    @GeneratedValue
    private long id;

    private String path;

    @ManyToOne
    @JsonBackReference
    private Project project;

    public ProjectFile(String path, Project project) {
        this.path = path;
        this.project = project;
    }
}
