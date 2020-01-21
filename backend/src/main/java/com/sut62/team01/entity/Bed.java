package com.sut62.team01.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@Table(name="BED")
public class Bed {
	@Id
	@SequenceGenerator(name="BED_seq",sequenceName="BEDS_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BED_seq")
	@Column(name="BED_ID",unique = true, nullable = true)
	private Long id;
	
	private @NotNull String bed;

	public Bed(){}
    public Bed(String bed){
        this.bed=bed;
    }

    
}
