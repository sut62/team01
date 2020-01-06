package com.sut62.team01.repository;

import com.sut62.team01.entity.BikeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * InnerBikeTypeRepository
 */
@RepositoryRestResource
public interface BikeTypeRepository extends JpaRepository<BikeType, Long>{

    
}