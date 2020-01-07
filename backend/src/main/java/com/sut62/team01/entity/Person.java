package com.sut62.team01.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Pattern(regexp = "\\d{13}")
    private String personId;


}
