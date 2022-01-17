package com.ecommerce.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderDTO implements Serializable {

    @JsonIgnore
    private Long id;

    private Long userId;

    private List<ProductDTO> products;

    private Long totalAmount;

    private Date createdDate;
}
