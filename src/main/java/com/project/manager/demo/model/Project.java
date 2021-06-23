package com.project.manager.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Project {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @OneToMany(mappedBy = "projectId")
    private Set<Task> tasks;

    @OneToMany
    @JoinTable(name = "project_student",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;

    public Project(long id, String name, String description, LocalDate returnDate, Set<Task> tasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.returnDate = returnDate;
        this.tasks = tasks;
    }

    public Project(long id, String name, String description, LocalDate returnDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.returnDate = returnDate;
    }

    public Project(String name, String description, LocalDate returnDate, Set<Task> tasks, List<Student> students) {
        this.name = name;
        this.description = description;
        this.returnDate = returnDate;
        this.tasks = tasks;
        this.students = students;
    }

    public Project(String name, String description, LocalDate returnDate) {
        this.name = name;
        this.description = description;
        this.returnDate = returnDate;
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
