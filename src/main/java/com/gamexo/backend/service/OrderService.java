package com.gamexo.backend.service;

import com.gamexo.backend.config.ResponseExeption;
import com.gamexo.backend.dto.order.CartDTO;
import com.gamexo.backend.dto.order.CustomerDTO;
import com.gamexo.backend.dto.order.OrderDTO;
import com.gamexo.backend.dto.order.ProductDTO;
import com.gamexo.backend.model.Order;
import com.gamexo.backend.model.UserEntity;
import com.gamexo.backend.repository.OrderRepository;
import com.gamexo.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public void save(OrderDTO orderDTO) {
        UserEntity user = getCurrentUser();

        if (user != null) {
            Order order = OrderDTO.fromOrder(orderDTO, user);
            orderRepository.save(order);
        } else {
            throw new ResponseExeption("404", "User not found in the system.");
        }
    }

    public List<OrderDTO> orderList() {
        return orderRepository.findAll()
                .stream()
                .map(OrderDTO::new)
                .toList();
    }

    public List<OrderDTO> myOrders() {
        UserEntity currentUser = getCurrentUser();
        if (currentUser == null) {
            return List.of();
        }

        return orderRepository.findAll().stream()
                .filter(order -> order.getCustomer().getId().equals(currentUser.getCustomer().getId()))
                .map(OrderDTO::new)
                .toList();
    }

    public OrderDTO itemCartOrder(Long orderId, Long itemCartId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseExeption("404", "Order not found"));

        ProductDTO product = order.getCart().getItemCartList().stream()
                .filter(itemCart -> itemCart.getId().equals(itemCartId))
                .map(ProductDTO::new)
                .findFirst()
                .orElseThrow(() -> new ResponseExeption("404", "ItemCart not found"));

        CartDTO filteredCartDTO = new CartDTO(
                order.getCart().getId(),
                order.getCart().getCreateAt(),
                order.getCart().getUpdateAt(),
                List.of(product)
        );

        return new OrderDTO(
                order.getId(),
                order.getCreditCard(),
                order.getPrice(),
                order.getCreateAt(),
                order.getCodeReference(),
                filteredCartDTO,
                CustomerDTO.fromDTO(order.getCustomer())
        );
    }


    private UserEntity getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository.findByEmail(email).orElse(null);
    }


}
