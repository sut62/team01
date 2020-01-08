package com.sut62.team01.repository;

import com.sut62.team01.entity.RoomBooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
    RoomBooking findById(long id);
}