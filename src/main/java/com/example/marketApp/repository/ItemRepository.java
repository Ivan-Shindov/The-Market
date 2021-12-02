package com.example.marketApp.repository;

import com.example.marketApp.model.entity.ItemEntity;
import com.example.marketApp.model.projection.AllItemsProjectionDTO;
import com.example.marketApp.model.projection.ItemProjectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query(value = "SELECT i.id as id, i.name as name, u.username as ownerName FROM market_db.items AS i " +
            "JOIN market_db.users AS u ON i.owner_id = u.id WHERE i.id = :id",
            nativeQuery = true)
    ItemProjectionDTO findByItemId(Long id);

    // JPQL query not from bonus.
    @Query("SELECT i FROM ItemEntity i LEFT JOIN FETCH i.owner o WHERE i.owner.id = :ownerId")
    List<ItemEntity> findAllByOwnerId(Long ownerId);

    // native query from bonus.
    @Query(value = "SELECT i.id AS id, i.name AS name, u.id AS ownerId, u.username AS ownerUsername " +
            "FROM market_db.items AS i JOIN market_db.users AS u ON u.id = i.owner_id WHERE u.id = :id",
            nativeQuery = true)
    List<AllItemsProjectionDTO> findAllItemsByOwnerId(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE ItemEntity i SET i.owner.id = :buyerId WHERE i.id = :itemId")
    void changeItemOwner(Long itemId, Long buyerId);
}
