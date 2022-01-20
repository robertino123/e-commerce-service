package com.ecommerce.api.service.impl;

import com.ecommerce.api.domain.Category;
import com.ecommerce.api.domain.Product;
import com.ecommerce.api.dto.ProductDTO;
import com.ecommerce.api.mapper.ProductMapper;
import com.ecommerce.api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductMapper productMapper;

    @Test
    public void searchProductsByQuery() {
        final String query = "test";
        final List<Product> productsEntity = List.of(new Product(1L, "123", "test", 12L, "testDescr", new Category(), 20));
        Mockito.when(productRepository.findAllBySearchedTerm('%' + query + '%')).thenReturn(productsEntity);

        List<ProductDTO> actual = productService.searchProductsByQuery(query);

        assertEquals(1, actual.size());
    }

    @Test
    public void searchProductsByQueryNoProductFound() {
        final String query = "test";
        List<ProductDTO> actual = productService.searchProductsByQuery(query);

        assertEquals(0, actual.size());
    }
}