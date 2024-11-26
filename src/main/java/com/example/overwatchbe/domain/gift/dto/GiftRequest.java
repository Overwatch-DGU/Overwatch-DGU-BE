package com.example.overwatchbe.domain.gift.dto;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
public class GiftRequest {
    private Long senderId;
    private String receiverEmail;
    private Long itemId;
}
