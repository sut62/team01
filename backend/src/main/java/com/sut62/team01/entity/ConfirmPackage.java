package com.sut62.team01.entity;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "ConfirmDate")
    private @NotNull Date confirmDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Staff.class)
    @JoinColumn(name = "STAFF_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Staff staff;

    public ConfirmPackage() {
    }

    public ConfirmPackage(PackageManagement packageManagement, Staff staff) {
        this.packageManagement = packageManagement;
        this.confirmDate = new Date();
        this.staff = staff;
    }
}