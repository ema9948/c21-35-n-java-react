package com.gamexo.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, length = 5000)
    private String description;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String img;

    private double rating;

    private String released;

    private String website;


}
