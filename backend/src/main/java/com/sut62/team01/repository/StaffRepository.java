package com.sut62.team01.repository;

import com.sut62.team01.entity.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StaffRepository extends JpaRepository<Staff, Long> {
	Staff findById(long id);
    Staff findByUsernameAndPassword(String username, String password);
}