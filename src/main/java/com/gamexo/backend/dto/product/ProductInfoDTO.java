package com.gamexo.backend.dto.product;

public record ProductInfoDTO(Long id,
                             String name,
                             int amount,
                             double price) {
}
