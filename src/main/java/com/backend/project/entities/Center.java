package com.backend.project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Center {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "CENTER_NAME")
    private String centerName;
    @Column(name = "CENTER_CODE")
    private String centerCode;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    @Column(name="STUDENT_CAPACITY")
    private int studentCapacity;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Courses> courses;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "EMAIL")
    private String email;
    @Column(name= "PHONE")
    private String phone;
}
