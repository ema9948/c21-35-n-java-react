package com.gamexo.backend.controller;

import com.gamexo.backend.model.Product;
import com.gamexo.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/games")
public class PublicController {

    private final ProductService productService;

    public PublicController(ProductService productService) {
        this.productService = productService;
    }
}
