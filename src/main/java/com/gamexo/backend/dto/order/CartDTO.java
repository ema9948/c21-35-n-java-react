package com.gamexo.backend.dto.order;

import com.gamexo.backend.model.Cart;

import java.time.LocalDateTime;
import java.util.List;

public record CartDTO(
        Long id,
        LocalDateTime createAt,
        LocalDateTime updateAt,
        List<ProductDTO> productDTOS
) {
    public CartDTO(Cart cart) {
        this(
                cart.getId(),
                cart.getCreateAt(),
                cart.getUpdateAt(),
                cart.getItemCartList().stream().map(ProductDTO::new).toList()
        );
    }

    static Cart fromCart(CartDTO cartDTO) {
        Cart cart = Cart.builder()
                .id(cartDTO.id())
                .createAt(cartDTO.createAt())
                .updateAt(cartDTO.updateAt())
                .itemCartList(ProductDTO.formItemCartList(cartDTO.productDTOS(), null)) // Cambiar null a cart después
                .build();

        cart.getItemCartList().forEach(itemCart -> itemCart.setCart(cart));

        return cart;
    }

    static CartDTO fromDTO(Cart cart) {
        return new CartDTO(cart);
    }
}
