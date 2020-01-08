package com.sut62.team01.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
/**
 * Room
 */

public class DateType {

    @Id
    @Column(name = "DATE_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotNull
    private String type;

    public DateType() {

    }

    public DateType(String type) {
        this.type = type;
    }
}