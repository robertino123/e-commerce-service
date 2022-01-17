package com.ecommerce.api.constraints.validator;

import com.ecommerce.api.constraints.ProductIdsConstraint;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIdsConstraintValidator implements ConstraintValidator<ProductIdsConstraint, Long> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.existsById(productId);
    }
}
