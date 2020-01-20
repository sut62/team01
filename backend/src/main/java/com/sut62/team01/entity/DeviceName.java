package com.sut62.team01.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.validation.constraints.NotNull;

@Data
@Entity
// @NoArgsConstructor
@Table(name = "DEVICENAME")
public class DeviceName {
    // TODO: แก้ DeviceProblem ID (PK) => DEVICEPROBLEMS_SEQ -> DEVICEPROBLEMS_ID
    @Id
    @SequenceGenerator(name = "DEVICENAMES_SEQ", sequenceName = "DEVICENAMES_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEVICENAMES_SEQ")
    @Column(name = "DEVICENAMES_ID", unique = true, nullable = true) // <--- แก้ให้แล้วนะ

    private  long id;

    private  @NotNull String name;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DeviceType.class)
    @JoinColumn(name = "DEVICETYPE_ID", insertable = true)
    @JsonManagedReference
    private DeviceType deviceType;

    // @OneToMany(fetch = FetchType.EAGER)
    // private Collection<Repair> repair;

    public DeviceName() {

    }

    public DeviceName(String name, DeviceType deviceType) {
        this.name = name;
        this.deviceType = deviceType;
    }

}
