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

@Data
@Entity
@NoArgsConstructor
@Table(name = "DEVICEPROBLEMS")
public class DeviceProblem {
    // TODO: แก้ DeviceProblem ID (PK) => DEVICEPROBLEMS_SEQ -> DEVICEPROBLEMS_ID
    @Id
    @SequenceGenerator(name = "DEVICEPROBLEMS_SEQ", sequenceName = "DEVICEPROBLEMS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEVICEPROBLEMS_SEQ")
    @Column(name = "DEVICEPROBLEMS_ID", unique = true, nullable = true) // <--- แก้ให้แล้วนะ

    private @NonNull long id;

    private @NonNull String problem;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DeviceType.class)
    @JoinColumn(name = "DEVICETYPE_ID", insertable = true)
    @JsonManagedReference
    private DeviceType deviceType;

    // @OneToMany(fetch = FetchType.EAGER)
    // private Collection<Repair> repair;

}