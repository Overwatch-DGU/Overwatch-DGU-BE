package com.example.overwatchbe.domain.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemResponse {
    private Long itemId;
    private String name;
    private String type;
    private String rarity;
    private int price;
    private String image;
    private String description;
}
