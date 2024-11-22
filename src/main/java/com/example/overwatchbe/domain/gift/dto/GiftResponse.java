package com.example.overwatchbe.domain.gift.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GiftResponse {
    private Long giftId;
    private String senderName;
    private String receiverName;
    private String itemName;
    private LocalDateTime giftedAt;
}
