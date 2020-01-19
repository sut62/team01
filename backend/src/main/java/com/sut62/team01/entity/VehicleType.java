package com.sut62.team01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "VEHICLE_TYPE")
public class VehicleType {

    @Id
    @SequenceGenerator(name="vehicle_type_seq",sequenceName="vehicle_type_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="vehicle_type_seq")  
    @Column(name = "VEHICLE_TYPE_ID", unique = true, nullable = true)
    private long id;

    private @NotNull String type;
}