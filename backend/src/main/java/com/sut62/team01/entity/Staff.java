package com.sut62.team01.entity;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "staff")
public class Staff{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STAFF_ID",insertable = true)
    private @NonNull long id;

    private @NonNull String name;
    private @NonNull String username;
    private @NonNull String password; 

     public Staff(){}
     public Staff(String name, String  user, String pass){
         this.name = name;
         this.password = pass;
         this.username = user;
    }
}