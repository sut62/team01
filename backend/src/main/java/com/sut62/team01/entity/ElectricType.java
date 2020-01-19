package com.sut62.team01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class ElectricType{

    @Id
    @SequenceGenerator(name="electicType_seq",sequenceName ="electicType_seq" )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "electicType_seq")
    @Column(name = "ElecticType_ID")
    private long id;

    private @NotNull String name;

    public ElectricType(){}
    public ElectricType(String name){
        this.name = name;
    }
}