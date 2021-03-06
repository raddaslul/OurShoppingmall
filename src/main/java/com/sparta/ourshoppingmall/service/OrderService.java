package com.sparta.ourshoppingmall.service;

import com.sparta.ourshoppingmall.domain.Order;
import com.sparta.ourshoppingmall.domain.Product;
import com.sparta.ourshoppingmall.domain.User;
import com.sparta.ourshoppingmall.repository.OrderRepository;
import com.sparta.ourshoppingmall.repository.ProductRepository;
import com.sparta.ourshoppingmall.repository.UserRepository;
import com.sparta.ourshoppingmall.responsedto.OrderProductResponseDto;
import com.sparta.ourshoppingmall.responsedto.OrderResponseDto;
import com.sparta.ourshoppingmall.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderValidator orderValidator;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, OrderValidator orderValidator) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderValidator = orderValidator;
    }


    @Transactional
    public Product createOrder(Long productId, User user) {
        Product product = productRepository.getById(productId);
        orderValidator.IsProductSold(product);
        orderValidator.validateOrder(product, user);

        product.setStatus(true);
        Order order = new Order();

        order.setProduct(product);
        order.setUser(user);
        product.setOrder(order);
        user.getOrders().add(order);

        orderRepository.save(order);
        userRepository.save(user);

        return product;
    }

    @Transactional
    public OrderResponseDto getOrders(User user) {
        Long userId = user.getId();
        List<Order> orders = orderRepository.getOrdersByUserId(userId);

        List<OrderProductResponseDto> products = new ArrayList<>();
        for(Order order : orders) {
            Product product = productRepository.getByOrderId(order.getId());
            User seller = product.getUser();

            OrderProductResponseDto orderProductResponseDto = new OrderProductResponseDto(product, seller, order);
            products.add(orderProductResponseDto);
        }
        OrderResponseDto orderResponseDto = new OrderResponseDto(products, user);
        return orderResponseDto;
    }
}