package com.project.manager.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String surname;

    @Column(name = "index_number", nullable = false, unique = true)
    private String indexNumber;

    @Column
    private String email;

    @Column(name = "is_full_time_student")
    private boolean isFullTimeStudent;

    public Student(String firstName, String surname, String indexNumber, String email, boolean isFullTimeStudent) {
        this.firstName = firstName;
        this.surname = surname;
        this.indexNumber = indexNumber;
        this.email = email;
        this.isFullTimeStudent = isFullTimeStudent;
    }
}