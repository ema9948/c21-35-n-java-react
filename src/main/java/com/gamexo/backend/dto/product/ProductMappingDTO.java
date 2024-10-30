package com.gamexo.backend.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true) // To ignore fields not needed
public record ProductMappingDTO (String name,
                                 @JsonProperty("description_raw")
                                 String description,
                                 @JsonProperty("background_image")
                                 String img,
                                 double rating,

                                 String released,
                                 String website){
}
