package com.banurns.skladbanurnsrest.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "requests")
public class Request extends BaseEntity{
    @Column(name = "storeid")
    private Long storeid;
    @Column(name = "flavors")
    private String flavors;
    @Column(name = "miscs")
    private String miscs;
    @Column(name = "creationuserid")
    private Long creationuserid;
    @Column(name = "description")
    private String description;
}
