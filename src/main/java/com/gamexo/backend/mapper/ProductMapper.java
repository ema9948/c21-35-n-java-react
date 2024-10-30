package com.gamexo.backend.mapper;

import com.gamexo.backend.dto.product.ProductInfoDTO;
import com.gamexo.backend.dto.product.ProductMappingDTO;
import com.gamexo.backend.dto.product.ProductRegistrationDTO;
import com.gamexo.backend.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {


    Product mapToProduct(ProductMappingDTO productMappingDTO, ProductRegistrationDTO productRegistrationDTO);

    List<ProductInfoDTO> toDtoList(List<Product> products);


}
