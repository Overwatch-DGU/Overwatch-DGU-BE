package com.example.overwatchbe.domain.log.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class GiftLogResponse {

    // GiftStatistics 데이터
    private Long userId;
    private int totalGiftsSent;
    private int totalGiftsReceived;
    private LocalDateTime lastGiftDate;

    // Gift 데이터
    private Long giftId;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String receiverName;
    private Long itemId;
    private String itemName;
    private LocalDateTime giftedAt;

//    // 추가 생성자 (GiftStatistics와 Gift 데이터를 함께 받기 위한 용도)
//    public GiftLogResponse(Long userId, int totalGiftsSent, int totalGiftsReceived, LocalDateTime lastGiftDate,
//                           Long giftId, Long senderId, Long receiverId, Long itemId, LocalDateTime giftedAt) {
//        this.userId = userId;
//        this.totalGiftsSent = totalGiftsSent;
//        this.totalGiftsReceived = totalGiftsReceived;
//        this.lastGiftDate = lastGiftDate;
//        this.giftId = giftId;
//        this.senderId = senderId;
//        this.receiverId = receiverId;
//        this.itemId = itemId;
//        this.giftedAt = giftedAt;
//    }
}
