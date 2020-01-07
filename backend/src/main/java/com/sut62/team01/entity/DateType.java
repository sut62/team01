package com.sut62.team01.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
/**
 * Room
 */

public class DateType {

    @Id
    @Column(name = "DATE_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @NotBlank
    @NonNull
    private String type;
}