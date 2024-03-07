package com.touhid.basedomains.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private long productId;
    private String name;
    private int quantity;
    private double price;
}
