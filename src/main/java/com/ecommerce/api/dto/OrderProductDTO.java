package com.ecommerce.api.dto;

import com.ecommerce.api.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Locale;

@Data
@AllArgsConstructor
public class OrderProductDTO {
    private String name;
    private String description;
    private String price;
    private String category;

    public OrderProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPricePerUnit().toString();
        this.category = product.getCategory().getName().toUpperCase(Locale.ROOT);
    }
}
