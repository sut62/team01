package com.sut62.team01.repository;

import com.sut62.team01.entity.DeviceType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {
    DeviceType findById(long id);
}