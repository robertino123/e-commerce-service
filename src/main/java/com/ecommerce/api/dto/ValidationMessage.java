package com.ecommerce.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationMessage {
    private Long productId;
    private List<String> errorMessage;
}
