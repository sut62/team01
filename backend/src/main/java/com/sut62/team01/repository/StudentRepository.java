package com.sut62.team01.repository;

import com.sut62.team01.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * StudentRepository
 */
@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Student findByUsernameAndPassword(String username, String password);
}