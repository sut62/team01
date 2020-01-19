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

@Data
@Entity
@Table(name = "packageType")
public class PackageType {
    @Id
    @SequenceGenerator(name = "packageType_seq", sequenceName = "packageType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "packageType_seq")
    @Column(name = "PACKAGETYPE_ID")
    private long id;

    private @NotNull String packageType;

    public PackageType() {
    }

    public PackageType(String packageType) {
        this.packageType = packageType;
    }
}