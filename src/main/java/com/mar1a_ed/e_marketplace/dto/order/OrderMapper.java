package com.mar1a_ed.e_marketplace.dto.order;

import com.mar1a_ed.e_marketplace.dto.orderItem.OrderItemResponseDto;
import com.mar1a_ed.e_marketplace.model.entity.Order;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toOrder(OrderCreateDto orderCreateDto){
        return new ModelMapper().map(orderCreateDto, Order.class);
    }

    public static OrderResponseDto toDto(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setClientId(order.getClient().getId());
        orderResponseDto.setTotalPrice(order.getTotalPrice());

        if(order.getItems() != null){
            List<OrderItemResponseDto> orderItems = order.getItems().stream().map(
                    orderItem -> {
                        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
                        orderItemResponseDto.setProductId(orderItem.getProduct().getId());
                        orderItemResponseDto.setQuantity(orderItem.getQuantity());
                        orderItemResponseDto.setSubtotal(orderItem.getSubTotal());

                        return orderItemResponseDto;
                    }
            ).collect(Collectors.toList());

            orderResponseDto.setItemsOrder(orderItems);
        }

        return orderResponseDto;
    }

    public static List<OrderResponseDto> toListDto(List<Order> orders){
        return orders.stream().map(order -> toDto(order)).collect(Collectors.toList());
    }
}
