package com.example.marketApp.model.entity;

import com.example.marketApp.model.projection.ActiveContractInfoProjection;
import com.example.marketApp.model.projection.ContractInfoProjection;

import javax.persistence.*;
import java.math.BigDecimal;
@NamedNativeQuery(name = "ContractEntity.getAllWithActiveTrue",
        query = "SELECT c.seller_id AS sellerId, s.username AS sellerUsername,i.id AS itemId, c.price AS price, c.active AS active " +
                "FROM market_db.contracts AS c " +
                "JOIN market_db.users AS s ON s.id = c.seller_id " +
                "JOIN market_db.items AS i ON i.id = c.item_id " +
                "WHERE c.active = true",
        resultSetMapping = "projection.ActiveContractInfoProjection")
@SqlResultSetMapping(name = "projection.ActiveContractInfoProjection",
        classes = @ConstructorResult(targetClass = ActiveContractInfoProjection.class,
                columns = {
                        @ColumnResult(name = "sellerId",type = Long.class),
                        @ColumnResult(name = "sellerUsername",type = String.class),
                        @ColumnResult(name = "itemId", type = Long.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                        @ColumnResult(name = "active",type = Boolean.class)
                }))
@Entity
@Table(name = "contracts")
public class ContractEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE})
    private UserEntity seller;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE})
    private UserEntity buyer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE})
    private ItemEntity item;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private boolean active;

    public ContractEntity(){}

    public UserEntity getSeller() {
        return seller;
    }

    public ContractEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public ContractEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }

    public ItemEntity getItem() {
        return item;
    }

    public ContractEntity setItem(ItemEntity item) {
        this.item = item;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ContractEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public ContractEntity setActive(boolean active) {
        this.active = active;
        return this;
    }
}
