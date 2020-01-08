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
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "DEVICEPROBLEMS")
public class DeviceProblem {
    @Id
    @SequenceGenerator(name = "DEVICEPROBLEMS_SEQ", sequenceName = "DEVICEPROBLEMS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEVICEPROBLEMS_SEQ")
    @Column(name = "DEVICEPROBLEMS_SEQ", unique = true, nullable = true)

    private @NonNull long id;

    private @NonNull String problem;

   

    @OneToMany(fetch = FetchType.EAGER)

    private Collection<Repair> repair;

    

}