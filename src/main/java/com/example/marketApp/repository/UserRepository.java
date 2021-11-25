package com.example.marketApp.repository;

import com.example.marketApp.model.projection.ViewProjectionUserDto;
import com.example.marketApp.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT new com.example.marketApp.model.projection.ViewProjectionUserDto(u.username, u.account) FROM UserEntity u WHERE u.id = :id")
    ViewProjectionUserDto getUserById(Long id);
}
