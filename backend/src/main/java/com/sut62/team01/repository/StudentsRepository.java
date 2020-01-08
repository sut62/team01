package com.sut62.team01.repository;

import com.sut62.team01.entity.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentsRepository extends JpaRepository<Students, Long> {
	Students findById(long id);
}