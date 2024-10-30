package com.gamexo.backend.dto.order;

import com.gamexo.backend.model.Order;
import com.gamexo.backend.model.UserEntity;

import java.time.LocalDateTime;

public record OrderDTO(
        Long id,
        String creditCard,
        Double price,
        LocalDateTime createAt,
        String codeReference,
        CartDTO cartDTO,
        CustomerDTO customer
) {

    public OrderDTO(Order order) {
        this(
                order.getId(),
                order.getCreditCard(),
                order.getPrice(),
                order.getCreateAt(),
                order.getCodeReference(),
                CartDTO.fromDTO(order.getCart()),
                CustomerDTO.fromDTO(order.getCustomer())
        );
    }

    public static Order fromOrder(OrderDTO orderDTO, UserEntity user) {
        return Order.builder()
                .creditCard(orderDTO.creditCard())
                .price(orderDTO.price())
                .createAt(orderDTO.createAt())
                .codeReference(orderDTO.codeReference())
                .cart(CartDTO.fromCart(orderDTO.cartDTO))
                .customer(user.getCustomer())
                .build();
    }
}
