package com.gamexo.backend.controller;

import com.gamexo.backend.dto.order.OrderDTO;
import com.gamexo.backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/order/")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody() OrderDTO orderDTO) {

        orderService.save(orderDTO);
        return ResponseEntity.ok("hola");
    }

    @GetMapping("all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(orderService.orderList());
    }

    @GetMapping("myOrders")
    public ResponseEntity<?> myOrders() {
        return ResponseEntity.ok(orderService.myOrders());
    }

    @GetMapping("{id}/itemCart/{itemCartId}")
    public ResponseEntity<?> myOrders(@PathVariable Long id, @PathVariable Long itemCartId) {
        return ResponseEntity.ok(orderService.itemCartOrder(id, itemCartId));
    }

}
