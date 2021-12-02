package com.example.marketApp.repository;

import com.example.marketApp.model.entity.UserEntity;
import com.example.marketApp.model.projection.UserWithoutItemsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u.id as id, u.username as username, u.account as account FROM UserEntity u WHERE u.id = :id")
    UserWithoutItemsProjection getUserById(Long id);

//    @Query("SELECT u.username AS username, u.account AS account FROM UserEntity u WHERE u.id = :id")
//    ItemProjectionDTO getProjection(Long id);

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.items WHERE u.id = :ownerId")
    Optional<UserEntity> findOwner(Long ownerId);

//    @Query("SELECT u.id FROM UserEntity u WHERE u.id = :buyerId")
//    Long isThereValidId(Long buyerId);

//    @Modifying
//    @Transactional
//    @Query("UPDATE UserEntity u SET u.id = :id, u.username = :username, u.account = :account")
//    Integer addUser(Long id, String username, BigDecimal account);
}
