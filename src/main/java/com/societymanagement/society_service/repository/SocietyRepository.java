package com.societymanagement.society_service.repository;

import com.societymanagement.society_service.entity.Society;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Long> {

    // Additional query methods can be defined here if needed
}
