package com.ecommerce.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

    @JsonIgnore
    private Long id;

    private String sku;

    private String name;

    private Long pricePerUnit;

    private String description;

    private CategoryDTO category;

    private Integer stock;

}
