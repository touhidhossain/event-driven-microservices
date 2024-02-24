package com.touhid.basedomains.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderEvent {
    private String status;
    private String message;
    private Order order;
}
