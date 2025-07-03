package com.societymanagement.society_service.repository;

import com.societymanagement.society_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    public Users getByFullName(String userName);
}
