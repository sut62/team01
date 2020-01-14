package com.sut62.team01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STAFF_ID", insertable = true)
    private long id;

    private @NotNull String name;
    private @NotNull String username;
    private @NotNull String password;

    public Staff() {
    }

    public Staff(String name, String user, String pass) {
        this.name = name;
        this.password = pass;
        this.username = user;
    }
}