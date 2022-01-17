package com.ecommerce.api.service.impl;

import com.ecommerce.api.dto.ProductDTO;
import com.ecommerce.api.mapper.ProductMapper;
import com.ecommerce.api.repository.ProductRepository;
import com.ecommerce.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> searchProductsByQuery(String query) {
        String searchedTerm = '%' + query + '%';
        return productRepository.findAllBySearchedTerm(searchedTerm)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
