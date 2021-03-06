package com.sparta.ourshoppingmall.responsedto;

import com.sparta.ourshoppingmall.domain.Order;
import com.sparta.ourshoppingmall.domain.Product;
import com.sparta.ourshoppingmall.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponseDto {
    Long productId;
    Boolean status;
    String title;
    String img;
    int price;
    String category;
    String address;
    String desc;
    LocalDateTime modifiedAt;
    Long productUserId;
    String productUsername;
    LocalDateTime orderCreatedAt;

    public OrderProductResponseDto(Product product, User user, Order order) {
        this.productId = product.getId();
        this.status = product.getStatus();
        this.title = product.getTitle();
        this.img = product.getImg();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.address = product.getAddress();
        this.desc = product.getDesc();
        this.modifiedAt = product.getModifiedAt();
        this.productUserId = user.getId();
        this.productUsername = user.getUsername();
        this.orderCreatedAt = order.getCreatedAt();
    }
}
