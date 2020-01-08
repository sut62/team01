package com.sut62.team01.repository;

import java.util.List;

import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Rooms;
import com.sut62.team01.entity.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
    RoomBooking findById(long id);

    List<RoomBooking> findByStudent(Students student);

    public List<RoomBooking> findByRooms(Rooms rooms);
}