package com.sut62.team01.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

import lombok.NonNull;
import javax.validation.constraints.NotNull;
@Data
@Entity

@Table(name = "DEVICETYPE")
public class DeviceType {
    @Id
    @SequenceGenerator(name = "DEVICETYPE_SEQ", sequenceName = "DEVICETYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEVICETYPE_SEQ")
    @Column(name = "DEVICETYPE_ID", unique = true, nullable = true)

    private  long id;

    private @NotNull String type;

    // TODO: DeviceType 1 - * DeviceProblem
    // @OneToMany
    // private Collection<DeviceProblem> deviceProblems;

    // @OneToMany(fetch = FetchType.EAGER)
    // private Collection<Repair> repair;
    
    public DeviceType(){}
    public DeviceType(String type){
        this.type = type;
    }

}