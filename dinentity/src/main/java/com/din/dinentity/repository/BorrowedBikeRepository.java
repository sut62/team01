package com.din.dinentity.repository;

import java.util.List;

import com.din.dinentity.entity.BorrowedBike;
import com.din.dinentity.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * InnerBorrowedBikeRepository
 */
@RepositoryRestResource
public interface BorrowedBikeRepository extends JpaRepository<BorrowedBike, Long> {

    public List<BorrowedBike> findByStudent(Student student);
}