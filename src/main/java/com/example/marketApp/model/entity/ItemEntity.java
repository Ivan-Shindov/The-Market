package com.example.marketApp.model.entity;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private UserEntity owner;

    public ItemEntity() {
    }

    public String getName() {
        return name;
    }

    public ItemEntity setName(String name) {
        this.name = name;
        return this;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public ItemEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public ItemEntity setId(Long id) {
        super.setId(id);
        return this;
    }
}
