package com.sut62.team01.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@Table(name="BRANCHES")
public class Branches {
	@Id
	@SequenceGenerator(name="BRANCHES_seq",sequenceName="BRANCHES_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BRANCHES_seq")
	@Column(name="BRANCHES_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String branches;

	public Branches(){}
    public Branches(String branches){
        this.branches=branches;
    }

    
}
