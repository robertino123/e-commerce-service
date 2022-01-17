package com.ecommerce.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderHistoryDTO {
    private Long userId;
    private List<OrderProductDTO> products;
    private Long totalAmount;
    private String processedDate;
}
