package com.example.overwatchbe.domain.gift.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiftItemResponse {
    private Long itemId;
    private String name;
    private String type;
    private String rarity;
    private int price;
    private String image;
    private String description;
}
