package com.ecommerce.api.dto;

import com.ecommerce.api.constraints.ProductIdsConstraint;
import com.ecommerce.api.constraints.QuantityValidation;
import lombok.Data;

import java.io.Serializable;

@QuantityValidation
@Data
public class ProductRequestDTO implements Serializable {

    @ProductIdsConstraint
    private Long productId;

    private Long quantity;
}
