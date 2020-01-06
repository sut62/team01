package com.din.dinentity.repository;

import com.din.dinentity.entity.BikeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * InnerBikeTypeRepository
 */
@RepositoryRestResource
public interface BikeTypeRepository extends JpaRepository<BikeType, Long>{

    
}