package com.example.overwatchbe.domain.gift.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GiftResponse {
    private String message;
    private Long itemId;
    private int remainingCoins;
}
