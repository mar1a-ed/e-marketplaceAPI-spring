package com.mar1a_ed.e_marketplace.controller;

import com.mar1a_ed.e_marketplace.dto.order.OrderCreateDto;
import com.mar1a_ed.e_marketplace.dto.order.OrderMapper;
import com.mar1a_ed.e_marketplace.dto.order.OrderResponseDto;
import com.mar1a_ed.e_marketplace.model.entity.Order;
import com.mar1a_ed.e_marketplace.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/make-a-order/clientId={clientId}")
    public ResponseEntity<OrderResponseDto> create(@Valid @PathVariable Long clientId, @RequestBody OrderCreateDto orderCreateDto){
        orderService.create(clientId, orderCreateDto);
        OrderMapper mapper = new OrderMapper();
        OrderResponseDto orderResponseDto = mapper.toDto(mapper.toOrder(orderCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }
}
