package com.sut62.team01.repository;


import com.sut62.team01.entity.Rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoomsRepository extends JpaRepository<Rooms, Long> {
	Rooms findById(long id);

	Rooms findByRoomId(String roomId);

}