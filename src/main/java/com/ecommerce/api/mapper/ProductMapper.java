package com.ecommerce.api.mapper;

import com.ecommerce.api.domain.Product;
import com.ecommerce.api.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {CategoryMapper.class}
)
public interface ProductMapper {
    ProductDTO toDto(Product product);

    Product toEntity(ProductDTO productDTO);
}
