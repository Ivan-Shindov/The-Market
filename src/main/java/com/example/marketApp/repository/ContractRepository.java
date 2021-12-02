package com.example.marketApp.repository;

import com.example.marketApp.model.entity.ContractEntity;
import com.example.marketApp.model.projection.ActiveContractInfoProjection;
import com.example.marketApp.model.projection.ContractInfoProjectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity,Long> {

    @Query(value = "SELECT c.id as id, s.username as seller, b.username as buyer, i.name as item, c.price as price, c.active as active " +
            "FROM market_db.contracts AS c JOIN market_db.users s ON c.seller_id = s.id " +
            "JOIN market_db.users b ON c.buyer_id = b.id " +
            "JOIN market_db.items i ON c.item_id = i.id WHERE c.id = :id",
            nativeQuery = true)
    ContractInfoProjectionDTO getContractById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE ContractEntity c SET c.price = :price " +
            "WHERE c.id = :id AND c.active = true AND c.item.id = :itemId")
    Integer updateActiveContractWithItemId(BigDecimal price, Long id, Long itemId);

    @Query("SELECT c FROM ContractEntity c WHERE c.id = :id")
    ContractEntity findContractById(Long id);

    @Query(nativeQuery = true)
    List<ActiveContractInfoProjection> getAllWithActiveTrue();

//    @Query(value = "SELECT s.id AS sellerId, s.username AS sellerUsername, " +
//            "b.id AS buyerId, b.username AS buyerUsername, " +
//            "i.id AS itemId, c.price AS price, c.active AS active" +
//            "FROM market_db.contracts AS c JOIN market_db.users AS s ON s.id = c.seller_id " +
//            "JOIN market_db.items AS i ON i.id = c.item_id " +
//            "JOIN market_db.users AS b ON b.id = c.buyer_id " +
//            "WHERE i.id = :itemId OR s.id = :sellerId OR b.id = c.buyer_id"
//            ,nativeQuery = true)
//    List<ContractProjection> findAllClosedContractsByOptionalParams(Long itemId, Long sellerId, Long buyerId);

    @Query("SELECT c FROM ContractEntity c LEFT JOIN FETCH c.item i " +
            "LEFT JOIN FETCH c.seller s " +
            "LEFT JOIN FETCH c.buyer b " +
            "WHERE c.active = false")
    List<ContractEntity> getALlClosed();

    @Query(value = "SELECT c.id AS id, s.username AS seller, b.username AS buyer, " +
            "i.name AS item, c.price AS price, c.active AS active" +
            " FROM market_db.contracts AS c " +
            "JOIN market_db.users AS s ON s.id = c.seller_id " +
            "JOIN market_db.users AS b ON b.id = c.buyer_id " +
            "JOIN market_db.items AS i ON i.id = c.item_id " +
            "WHERE s.id = :sellerId",
            nativeQuery = true)
    List<ContractInfoProjectionDTO> findAllBySellerId(Long sellerId);

}
