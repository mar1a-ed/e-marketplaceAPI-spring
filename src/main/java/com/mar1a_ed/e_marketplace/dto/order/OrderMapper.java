package com.mar1a_ed.e_marketplace.dto.order;

import com.mar1a_ed.e_marketplace.model.entity.Order;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public Order toOrder(OrderCreateDto orderCreateDto){
        return new ModelMapper().map(orderCreateDto, Order.class);
    }

    public OrderResponseDto toDto(Order order){
        return new ModelMapper().map(order, OrderResponseDto.class);
    }

    public List<OrderResponseDto> toListDto(List<Order> orders){
        return orders.stream().map(order -> toDto(order)).collect(Collectors.toList());
    }
}
