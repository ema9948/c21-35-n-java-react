package com.gamexo.backend.controller;

import com.gamexo.backend.dto.product.ProductInfoDTO;
import com.gamexo.backend.dto.product.ProductRegistrationDTO;
import com.gamexo.backend.model.Product;
import com.gamexo.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getGames/{name}")
    public ResponseEntity<Map<Long,String>> getGamesByName(@PathVariable String name){
        Map<Long, String> products = productService.searchGameNamesByName(name);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addGame(@RequestBody ProductRegistrationDTO registrationDTO, UriComponentsBuilder uriComponentsBuilder){
        Product product = productService.addGame(registrationDTO);
        URI url = uriComponentsBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(url).body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getGame(@PathVariable Long id){
        Product product = productService.getSingleProduct(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductInfoDTO>> getGames(){
        List<ProductInfoDTO> productInfoDTOS = productService.getProducts();
        return ResponseEntity.ok(productInfoDTOS);
    }

    // pending PUT, DELETE method

}
