package com.example.overwatchbe.domain.shop.dto;

import lombok.Data;

@Data
public class BuyRequest {
    private Long userId;
    private Long itemId;
}
