package com.example.marketApp.repository;

import com.example.marketApp.model.entity.ContractEntity;
import com.example.marketApp.model.projection.ContractInfoDTO;
import com.example.marketApp.model.projection.ContractInfoProjectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity,Long> {

    @Query(value = "SELECT c.id as id, s.username as seller, b.username as buyer, i.name as item, c.price as price, c.active as active " +
            "FROM market_db.contracts AS c JOIN market_db.users s ON c.seller_id = s.id " +
            "JOIN market_db.users b ON c.buyer_id = b.id " +
            "JOIN market_db.items i ON c.item_id = i.id WHERE c.id = :id",
            nativeQuery = true)
    ContractInfoProjectionDTO getContractById(Long id);

}
