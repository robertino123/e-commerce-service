package com.ecommerce.api.service;

import com.ecommerce.api.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> searchProductsByQuery(String query);
}
