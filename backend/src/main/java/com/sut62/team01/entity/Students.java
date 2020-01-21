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
@Table(name = "STUDENTS")
public class Students {

	@Id
	@SequenceGenerator(name = "STUDENTS_seq", sequenceName = "STUDENTS_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENTS_seq")
	@Column(name = "STUDENTS_ID", unique = true, nullable = true)
	private Long id;
	
	@NotNull
	private String studentId;

	@NotNull
	private String fullName;

	@NotNull
	private String username;

	@NotNull
	private String password;

	public Students() {
	}

	// public Students(String students) {
	// 	//this.studentId = students;
	// }

	public Students(String fullName, String studentId, String username, String password) {
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.studentId = studentId;
	}
}
