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
@Table(name="ROOMS")
public class Rooms {
	@Id
	@SequenceGenerator(name="ROOMS_seq",sequenceName="ROOMS_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROOMS_seq")
	@Column(name="ROOMS_ID",unique = true, nullable = true)
	private Long id;

	private @NotNull String roomId;

	public Rooms(){}

	public Rooms(String roomId) {
		this.roomId = roomId;
	}
}
