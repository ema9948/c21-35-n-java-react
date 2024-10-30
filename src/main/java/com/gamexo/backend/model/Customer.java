package com.gamexo.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(orphanRemoval = true, mappedBy = "customer")
    private Set<Order> orders;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
