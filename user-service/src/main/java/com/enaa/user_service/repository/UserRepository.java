package com.enaa.user_service.repository;

import com.enaa.user_service.dto.UserDto;
import com.enaa.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  UserRepository extends  JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}
