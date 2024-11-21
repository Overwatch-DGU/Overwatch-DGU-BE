package com.example.overwatchbe.domain.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyResponse {
    private int remainingCoins;
    private String message;
}
