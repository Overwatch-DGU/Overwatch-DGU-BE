package com.example.overwatchbe.domain.gift.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftRequest {
    private Long senderId;
    private Long receiverId;
    private Long itemId;
}
