package com.gamexo.backend.dto.order;

import com.gamexo.backend.model.Cart;
import com.gamexo.backend.model.ItemCart;

import java.util.List;

public record ProductDTO(
        Long id,
        String name,
        String description,
        int amount,
        double price,
        String img
) {
    public ProductDTO(ItemCart itemCart) {
        this(
                itemCart.getId(),
                itemCart.getName(),
                itemCart.getDescription(),
                itemCart.getAmount(),
                itemCart.getPrice(),
                itemCart.getImg()
        );
    }

    static List<ItemCart> formItemCartList(List<ProductDTO> productDTOS, Cart cart) {
        return productDTOS.stream().map(productDTO ->
                ItemCart.builder()
                        .cart(cart)
                        .id(productDTO.id())
                        .name(productDTO.name())
                        .description(productDTO.description())
                        .amount(productDTO.amount())
                        .price(productDTO.price())
                        .img(productDTO.img())
                        .build()).toList();
    }
}
