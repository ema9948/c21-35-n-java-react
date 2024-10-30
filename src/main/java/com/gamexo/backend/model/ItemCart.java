package com.gamexo.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itemCart")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String img;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
