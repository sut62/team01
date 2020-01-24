package com.sut62.team01.repository;

import com.sut62.team01.entity.PackageManagement;
import com.sut62.team01.entity.RoomBooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PackageManagementRepository extends JpaRepository<PackageManagement, Long> {
    PackageManagement findById(long id);
    PackageManagement findByRoomBooking(RoomBooking roomBooking);
}