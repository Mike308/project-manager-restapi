package com.project.manager.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    @JsonManagedReference
    private List<Task> tasks;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private List<ProjectFile> projectFiles;


    @ManyToMany
    @JoinTable(name = "project_student",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;

    public Project(long id, String name, String description, LocalDate returnDate, List<Task> tasks) {
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

    public Project(String name, String description, LocalDate returnDate, List<Task> tasks, List<Student> students) {
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

    public Project removeStudentFromProject(long studentId) {
        if (students == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Do projektu nie przydzielono studentów");
        if (students.parallelStream().anyMatch(student -> student.getId() == studentId)) {
            this.students = this.students.parallelStream().filter(student -> student.getId() != studentId).collect(Collectors.toList());
            return this;
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono studenta");
    }
}
