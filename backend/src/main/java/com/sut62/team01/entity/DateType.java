package com.sut62.team01.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Data
/**
 * Room
 */
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"type"}))
public class DateType {

    @Id
    @Column(name = "DATE_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 20)

    private String type;

    public DateType() {}

    public DateType(String type) {
        this.type = type;
    }
}