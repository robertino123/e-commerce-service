package com.ecommerce.api.service;

import com.ecommerce.api.dto.OrderHistoryDTO;
import com.ecommerce.api.dto.ProductRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserOrderService {
    ResponseEntity processOrder(List<ProductRequestDTO> productRequests, String accountNumber);

    List<OrderHistoryDTO> getOrderHistory(Long userId);
}
