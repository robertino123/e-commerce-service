package com.ecommerce.api.constraints.validator;

import com.ecommerce.api.constraints.QuantityValidation;
import com.ecommerce.api.domain.Product;
import com.ecommerce.api.dto.ProductRequestDTO;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class QuantityValidator implements ConstraintValidator<QuantityValidation, ProductRequestDTO> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public boolean isValid(ProductRequestDTO productRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Product> productOptional = productRepository.findById(productRequestDTO.getProductId());
        return productOptional.map(product -> product.getStock() != 0).orElse(true);
    }
}
