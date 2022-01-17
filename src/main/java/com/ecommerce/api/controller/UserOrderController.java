package com.ecommerce.api.controller;

import com.ecommerce.api.dto.OrderHistoryDTO;
import com.ecommerce.api.dto.ProductRequestDTO;
import com.ecommerce.api.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class UserOrderController {

    @Autowired
    UserOrderService userOrderService;

    @PostMapping("/buy")
    public ResponseEntity processOrder(@Valid @RequestBody List<ProductRequestDTO> productRequests, @RequestParam String accountNumber) {
        return userOrderService.processOrder(productRequests, accountNumber);
    }

    @GetMapping("/history")
    public List<OrderHistoryDTO> getHistory(@RequestParam Long userId) {
        return userOrderService.getOrderHistory(userId);
    }
}
