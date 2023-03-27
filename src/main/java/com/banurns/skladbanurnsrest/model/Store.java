package com.banurns.skladbanurnsrest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stores")
public class Store extends BaseEntity{
    @Column(name = "name")
    private String name;
}
