package com.banurns.skladbanurnsrest.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "Flavors")
public class Flavor extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "barcode")
    private String barcode;
}
