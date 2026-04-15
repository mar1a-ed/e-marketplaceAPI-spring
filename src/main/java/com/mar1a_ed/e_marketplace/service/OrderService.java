package com.mar1a_ed.e_marketplace.service;

import com.mar1a_ed.e_marketplace.dto.order.OrderCreateDto;
import com.mar1a_ed.e_marketplace.dto.orderItem.OrderItemCreateDto;
import com.mar1a_ed.e_marketplace.dto.orderItem.OrderItemMapper;
import com.mar1a_ed.e_marketplace.exception.ClientNotFoundException;
import com.mar1a_ed.e_marketplace.exception.OrderNotFoundException;
import com.mar1a_ed.e_marketplace.exception.ProductNotFoundException;
import com.mar1a_ed.e_marketplace.exception.StockQuantityUnavailableException;
import com.mar1a_ed.e_marketplace.model.entity.Client;
import com.mar1a_ed.e_marketplace.model.entity.Order;
import com.mar1a_ed.e_marketplace.model.entity.OrderItem;
import com.mar1a_ed.e_marketplace.model.entity.Product;
import com.mar1a_ed.e_marketplace.model.enums.Status;
import com.mar1a_ed.e_marketplace.repository.ClientRepository;
import com.mar1a_ed.e_marketplace.repository.OrderRepository;
import com.mar1a_ed.e_marketplace.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order create(Long clientId, OrderCreateDto orderCreateDto){
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ClientNotFoundException(String.format("Client {id=%s} not found.", clientId))
        );

        Order order = new Order();
        order.setClient(client);

        List<OrderItem> orderItems = orderCreateDto.getItemsOrder().stream().map(orderItemCreateDto -> {
            Product product = productRepository.findById(orderItemCreateDto.getProductId()).orElseThrow(
                    () -> new ProductNotFoundException("Product not found.")
            );

            if(product.getStockQuantity() >= orderItemCreateDto.getQuantity()){
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(orderItemCreateDto.getQuantity());

                BigDecimal subtotal = product.getCurrentPrice().multiply(BigDecimal.valueOf(orderItemCreateDto.getQuantity()));
                orderItem.setSubTotal(subtotal);

                return orderItem;
            }else{
                throw new StockQuantityUnavailableException("");
            }

        }).toList();

        order.setItems(orderItems);

        BigDecimal totalPrice = orderItems.stream().map(OrderItem::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);

    }

    @Transactional(readOnly = true)
    public Order findByOrderId(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order {id=%s} not found", orderId))
        );
    }

    @Transactional(readOnly = true)
    public List<Order> findByClientId(Long clientId){
        try{
            List<Order> orders = orderRepository.findByClientId(clientId);
            return orders;
        }catch (Exception e){
            throw new OrderNotFoundException(String.format("Order(s) not found for client id=%s",clientId));
        }
    }

    @Transactional
    public void updateStatusConfirmed(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order {id=%s} not found",orderId))
        );

        order.getItems().forEach(orderItem -> {
            Product product = orderItem.getProduct();
            product.setStockQuantity(product.getStockQuantity() - orderItem.getQuantity());
        });

        order.setStatus(Status.CONFIRMED);
        orderRepository.save(order);
    }

    @Transactional
    public void updateStatusSeparating(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order {id=%s} not found",orderId))
        );

        order.setStatus(Status.SEPARATING);
        orderRepository.save(order);
    }

    @Transactional
    public void updateStatusOutForDelivery(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order {id=%s} not found",orderId))
        );

        order.setStatus(Status.OUT_FOR_DELIVERY);
        orderRepository.save(order);
    }

    @Transactional
    public void updateStatusDelivered(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order {id=%s} not found",orderId))
        );


        order.setStatus(Status.DELIVERED);
        orderRepository.save(order);
    }

    @Transactional
    public void updateStatusCanceled(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order {id=%s} not found",orderId))
        );

        order.getItems().forEach(orderItem -> {
            Product product = orderItem.getProduct();
            product.setStockQuantity(product.getStockQuantity() + orderItem.getQuantity());
        });

        order.setStatus(Status.CANCELED);
        orderRepository.save(order);
    }
}
