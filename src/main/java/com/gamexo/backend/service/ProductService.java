package com.gamexo.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamexo.backend.dto.product.ProductInfoDTO;
import com.gamexo.backend.dto.product.ProductMappingDTO;
import com.gamexo.backend.dto.product.ProductRegistrationDTO;
import com.gamexo.backend.mapper.ProductMapper;
import com.gamexo.backend.model.Product;
import com.gamexo.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    public Map<Long, String> searchGameNamesByName(String searchName) {
        String apiUrlTemplate = "https://api.rawg.io/api/games?key=5a32edd556024b7ba4883801bfffc584&page={page}&page_size=50";
        int currentPage = 1;
        boolean morePages = true;
        Map<Long, String> matchingGames = new HashMap<>();
        int MAX_PAGES = 10;

        try {
            while (morePages && currentPage <= MAX_PAGES) {
                String apiUrl = apiUrlTemplate.replace("{page}", String.valueOf(currentPage));
                String response = restTemplate.getForObject(apiUrl, String.class);

                // Parse the JSON response
                JsonNode rootNode = objectMapper.readTree(response);
                JsonNode resultsNode = rootNode.path("results");

                // Add matching game names
                for (JsonNode gameNode : resultsNode) {
                    String gameName = gameNode.path("name").asText();
                    if (gameName.toLowerCase().contains(searchName.toLowerCase())) {
                        matchingGames.put(gameNode.path("id").asLong(), gameName);
                    }
                }

                // Check if there are more pages
                JsonNode nextNode = rootNode.path("next");
                morePages = !nextNode.isNull();
                currentPage++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matchingGames;
    }


    public Product addGame(ProductRegistrationDTO registrationDTO) {

        String apiUrl = String.format("https://api.rawg.io/api/games/%d?key=5a32edd556024b7ba4883801bfffc584", registrationDTO.id());

        try {
            ProductMappingDTO productMappingDTO = restTemplate.getForObject(apiUrl, ProductMappingDTO.class);
            Product product = productMapper.mapToProduct(productMappingDTO, registrationDTO);
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product getSingleProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
    }

    public List<ProductInfoDTO> getProducts(){
        List<Product> products =  productRepository.findAll();
        return productMapper.toDtoList(products);
    }
}
