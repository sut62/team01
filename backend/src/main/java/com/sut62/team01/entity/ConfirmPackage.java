package com.sut62.team01.entity;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "confirmPackage")
public class ConfirmPackage {
    @Id
    @SequenceGenerator(name = "confirmPackage_seq", sequenceName = "confirmPackage_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmPackage_seq")
    @Column(name = "CONFIRMPACKAGE_ID", unique = true, nullable = true, insertable = true)
    private @NotNull long id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = PackageManagement.class)
    @JoinColumn(name = "PACKAGEMANAMENT_ID", insertable = true)
    @JsonManagedReference
    private @NotNull PackageManagement packageManagement;

    @Column(name = "ConfirmDate")
    private @NotNull Date confirmDate;

    public ConfirmPackage() {
    }

    public ConfirmPackage(PackageManagement packageManagement) {
        this.packageManagement = packageManagement;
        this.confirmDate = new Date();
    }
}