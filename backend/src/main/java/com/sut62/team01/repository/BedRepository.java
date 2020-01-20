package com.sut62.team01.repository;

import com.sut62.team01.entity.Bed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BedRepository extends JpaRepository<Bed, Long> {
	Bed findById(long id);
}