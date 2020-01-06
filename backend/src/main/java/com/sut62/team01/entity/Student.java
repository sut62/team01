package com.sut62.team01.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "STUDENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @NotBlank
    @NonNull
    private String name;

    @NotBlank
    @NonNull
    private String bachelor;

    @NotBlank
    @Column(name = "username", unique = true)
    @NonNull
    private String username;

    @Column(name = "password")
    @NotBlank
    @NonNull
    private String password;

}