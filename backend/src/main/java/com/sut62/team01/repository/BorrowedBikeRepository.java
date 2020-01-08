package com.sut62.team01.repository;

// import java.util.List;

import com.sut62.team01.entity.BorrowedBike;
// import com.sut62.team01.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * InnerBorrowedBikeRepository
 */
@RepositoryRestResource
public interface BorrowedBikeRepository extends JpaRepository<BorrowedBike, Long> {

    // public List<BorrowedBike> findByStudent(Student student);
}