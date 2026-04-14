package com.mar1a_ed.e_marketplace.dto.orderItem;

import com.mar1a_ed.e_marketplace.model.entity.OrderItem;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderItemMapper {

    public OrderItem toOrder(OrderItemCreateDto orderItemCreateDtoCreateDto){
        return new ModelMapper().map(orderItemCreateDtoCreateDto, OrderItem.class);
    }

    public OrderItemResponseDto toDto(OrderItem orderItem){
        return new ModelMapper().map(orderItem, OrderItemResponseDto.class);
    }

    public List<OrderItemResponseDto> toListDto(List<OrderItem> orderItems){
        return orderItems.stream().map(orderItem -> toDto(orderItem)).collect(Collectors.toList());
    }
}
