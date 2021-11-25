package com.example.marketApp.repository;

import com.example.marketApp.model.dto.ViewItemDto;
import com.example.marketApp.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query("SELECT i FROM ItemEntity i JOIN FETCH i.owner o WHERE i.id = :id")
    Optional<ItemEntity> findByItemId(Long id);

    @Query("SELECT i FROM ItemEntity i JOIN FETCH i.owner o WHERE i.owner.id = :ownerId")
    List<ItemEntity> findAllByOwnerId(Long ownerId);
}
