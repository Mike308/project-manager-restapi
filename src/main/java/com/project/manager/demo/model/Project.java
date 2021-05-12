package com.project.manager.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="project")

public class Project {

    @Id
    @GeneratedValue
    @Column(name=czy "project_id)
            private long projectId;

            @Column(nullable = false, length = 50)
            private String name;

            @Column(length = 1000)
            private String description;

            @Column(nullable = false)
            private LocalDateTime creationDate;

            @Column
            private LocalDate returnDate;

            @OneToMany(mappedBy = "project")
            private List<Task> tasks;

     /*       public Student(String firstName, String surname, String indexNumber, String email, boolean isFullTimeStudent) {
        this.firstName = firstName;
        this.surname = surname;
        this.indexNumber = indexNumber;
        this.email = email;
        this.isFullTimeStudent = isFullTimeStudent;
        tu nie wiem jak zrobić  = normalnie bym zrobil tak:
        @JoinTable(name = "project_students", joinColumns ={@JoinColumn(name="project_id")],
        inverseJoinColumns = {@JoinColumn(name = "student_id")])

      */



}
