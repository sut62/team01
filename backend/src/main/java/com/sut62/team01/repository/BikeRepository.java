package com.sut62.team01.repository;

import java.util.Collection;

import com.sut62.team01.entity.Bike;
import com.sut62.team01.entity.BikeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BikeRepository extends JpaRepository<Bike, Long> {
    Collection<Bike> findByBikeType(BikeType bikeType);
}
