package com.example.overwatchbe.domain.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterResponse {
    private Long characterId;
    private String name;
    private String role;
    private String image;
    private String description;
}
