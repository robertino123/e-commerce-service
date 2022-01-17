package com.ecommerce.api.service.impl;

import com.ecommerce.api.domain.Product;
import com.ecommerce.api.domain.UserOrder;
import com.ecommerce.api.dto.OrderHistoryDTO;
import com.ecommerce.api.dto.OrderProductDTO;
import com.ecommerce.api.dto.ProductRequestDTO;
import com.ecommerce.api.dto.ValidationMessage;
import com.ecommerce.api.feignclient.AccountClient;
import com.ecommerce.api.feignclient.FundTransferClient;
import com.ecommerce.api.repository.ProductRepository;
import com.ecommerce.api.repository.UserOrderRepository;
import com.ecommerce.api.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    public static final String ECOMMERCE_ACC_NO = "5859";

    @Autowired
    FundTransferClient fundTransferClient;

    @Autowired
    AccountClient accountClient;

    @Autowired
    UserOrderRepository userOrderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Validator validator;

    @Override
    public ResponseEntity processOrder(List<ProductRequestDTO> productRequests, String accountNumber) {

        Map<Long, List<String>> validationMessages = validateRequests(productRequests);

        if (!validationMessages.isEmpty()) {
            return getErrors(validationMessages);
        } else {
            List<Long> productIds = productRequests.stream().map(ProductRequestDTO::getProductId).collect(Collectors.toList());
            List<Product> allProducts = productRepository.findAllById(productIds);

            AtomicReference<Long> totalAmount = new AtomicReference<>(0L);

            allProducts.forEach(product -> {
                calculateAmountAndUpdateStock(productRequests, totalAmount, product);
            });

            String fundTransferResponse = fundTransferClient.transferFunds(accountNumber, ECOMMERCE_ACC_NO, totalAmount.get().toString(), "Order payment");

            saveNewOrder(allProducts, totalAmount, accountNumber);

            return ResponseEntity.ok(fundTransferResponse);
        }
    }

    @Override
    public List<OrderHistoryDTO> getOrderHistory(Long userId) {
        return userOrderRepository.findAllByUserId(userId)
                .stream()
                .map(this::getHistory)
                .collect(Collectors.toList());
    }

    private OrderHistoryDTO getHistory(UserOrder order) {
        List<OrderProductDTO> products = order.getProducts()
                .stream()
                .map(OrderProductDTO::new)
                .collect(Collectors.toList());
        return new OrderHistoryDTO(order.getUserId(), products, order.getTotalAmount(), getDate(order.getCreatedDate()));
    }

    private String getDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return format.format(date);
    }

    private void calculateAmountAndUpdateStock(List<ProductRequestDTO> productRequests, AtomicReference<Long> totalAmount, Product product) {
        Optional<ProductRequestDTO> productRequestDTO = productRequests.stream().filter(p -> p.getProductId().equals(product.getId())).findFirst();
        productRequestDTO.ifPresent(requestDTO -> {
            totalAmount.updateAndGet(v -> v + requestDTO.getQuantity() * product.getPricePerUnit());
            long remainingStock = product.getStock() - requestDTO.getQuantity();
            product.setStock(Math.toIntExact(remainingStock));
            productRepository.save(product);
        });
    }

    private void saveNewOrder(List<Product> allProducts, AtomicReference<Long> totalAmount, String accountNumber) {
        Long userId = accountClient.getUserId(accountNumber);
        UserOrder userOrder = new UserOrder(userId, allProducts, totalAmount.get(), new Date());
        userOrderRepository.save(userOrder);
    }

    private ResponseEntity<List<ValidationMessage>> getErrors(Map<Long, List<String>> validationMessages) {
        List<ValidationMessage> validationMessagesToReturn = new ArrayList<>();
        validationMessages.forEach((id, messageList) -> {
            validationMessagesToReturn.add(new ValidationMessage(id, messageList));
        });

        return ResponseEntity.badRequest().body(validationMessagesToReturn);
    }

    private Map<Long, List<String>> validateRequests(List<ProductRequestDTO> productRequests) {
        Map<Long, List<String>> validations = new HashMap<>();
        productRequests.forEach(pr -> {
                    Set<ConstraintViolation<ProductRequestDTO>> constraintViolations = validator.validate(pr);
                    if (!constraintViolations.isEmpty())
                        validations.put(pr.getProductId(), constraintViolations.stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList()));
                }
        );
        return validations;
    }


}
