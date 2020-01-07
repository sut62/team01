package com.sut62.team01.repository;

import com.sut62.team01.entity.PackageType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PackageTypeRepository extends JpaRepository<PackageType, Long> {
	PackageType findById(long id);
}