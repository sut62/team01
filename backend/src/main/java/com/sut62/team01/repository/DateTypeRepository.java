package com.sut62.team01.repository;

import com.sut62.team01.entity.DateType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * InnerBikeTypeRepository
 */
@RepositoryRestResource
public interface DateTypeRepository extends JpaRepository<DateType, Long> {

}