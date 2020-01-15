package com.sut62.team01.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * BikeType
 */
@Entity
@Data

public class BikeType {

    @Id
    @Column(name = "BIKETYPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5,max = 30)
    private String type;

    public BikeType() {
    }

    public BikeType(String type) {
        this.type = type;
    }

}