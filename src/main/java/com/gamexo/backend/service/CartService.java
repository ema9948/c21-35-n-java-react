package com.gamexo.backend.service;


import com.gamexo.backend.config.ResponseExeption;
import com.gamexo.backend.dto.order.CartDTO;
import com.gamexo.backend.dto.order.ProductDTO;
import com.gamexo.backend.model.Cart;
import com.gamexo.backend.model.ItemCart;
import com.gamexo.backend.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartDTO getCart(Long id) {
        return cartRepository.findById(id)
                .map(CartDTO::new)
                .orElseThrow((() -> new ResponseExeption("404", "Cart Id not found")));
    }


    public CartDTO itemCart(Long cartId, Long itemCartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResponseExeption("404", "Cart Id not found"));

        ItemCart itemCart = cart.getItemCartList().stream()
                .filter(item -> item.getId().equals(itemCartId))
                .findFirst()
                .orElseThrow(() -> new ResponseExeption("404", "Item Cart not found"));

        return new CartDTO(cart.getId(), cart.getCreateAt(), cart.getUpdateAt(), List.of(new ProductDTO(itemCart)));
    }


}
