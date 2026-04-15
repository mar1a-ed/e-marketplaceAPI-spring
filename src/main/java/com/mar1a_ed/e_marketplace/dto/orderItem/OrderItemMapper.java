package com.mar1a_ed.e_marketplace.dto.orderItem;

import com.mar1a_ed.e_marketplace.model.entity.OrderItem;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderItemMapper {

    public static OrderItem toOrder(OrderItemCreateDto orderItemCreateDtoCreateDto){
        return new ModelMapper().map(orderItemCreateDtoCreateDto, OrderItem.class);
    }

    public static OrderItemResponseDto toDto(OrderItem orderItem){
        return new ModelMapper().map(orderItem, OrderItemResponseDto.class);
    }

    public static List<OrderItemResponseDto> toListDto(List<OrderItem> orderItems){
        return orderItems.stream().map(orderItem -> toDto(orderItem)).collect(Collectors.toList());
    }
}
