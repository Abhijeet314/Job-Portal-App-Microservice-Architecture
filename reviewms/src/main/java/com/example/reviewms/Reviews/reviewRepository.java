package com.example.reviewms.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface reviewRepository extends JpaRepository<review, Integer> {
    List<review> findByCompanyId(int id);
}
