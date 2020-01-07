package com.sut62.team01.repository;

import com.sut62.team01.entity.ElectricType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ElectricTypeRepository extends JpaRepository<ElectricType , Long>{
    ElectricType findById(long id);
}