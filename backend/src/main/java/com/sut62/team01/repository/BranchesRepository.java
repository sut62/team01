package com.sut62.team01.repository;

import com.sut62.team01.entity.Branches;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BranchesRepository extends JpaRepository<Branches, Long> {
	Branches findById(long id);
}