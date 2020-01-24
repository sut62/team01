package com.sut62.team01.repository;

import com.sut62.team01.entity.ConfirmPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ConfirmPackageRepository extends JpaRepository<ConfirmPackage, Long> {
}