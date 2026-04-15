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

import java.util.List;

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

    @GetMapping("/get/order_id={orderId}")
    public ResponseEntity<OrderResponseDto> findByOrderId(@Valid @PathVariable Long orderId){
        Order order = orderService.findByOrderId(orderId);
        return ResponseEntity.ok().body(OrderMapper.toDto(order));
    }

    @GetMapping("/get/client_id={clientId}")
    public ResponseEntity<List<OrderResponseDto>> findByClientId(@Valid @PathVariable Long clientId){
        List<Order> orders = orderService.findByClientId(clientId);
        return ResponseEntity.ok().body(OrderMapper.toListDto(orders));
    }

    @PatchMapping("/update-status=confirmed")
    public ResponseEntity<Void> updateStatusConfirmed(@Valid @PathVariable Long orderId){
        orderService.updateStatusConfirmed(orderId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-status=separating")
    public ResponseEntity<Void> updateStatusSeparating(@Valid @PathVariable Long orderId){
        orderService.updateStatusSeparating(orderId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-status=out_for_delivery")
    public ResponseEntity<Void> updateStatusOutForDelivery(@Valid @PathVariable Long orderId){
        orderService.updateStatusOutForDelivery(orderId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-status=delivered")
    public ResponseEntity<Void> updateStatusDelivered(@Valid @PathVariable Long orderId){
        orderService.updateStatusDelivered(orderId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-status=canceled")
    public ResponseEntity<Void> updateStatusCanceled(@Valid @PathVariable Long orderId){
        orderService.updateStatusCanceled(orderId);
        return ResponseEntity.noContent().build();
    }
}
