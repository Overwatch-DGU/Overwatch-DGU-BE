package com.example.overwatchbe.domain.log.service;

import com.example.overwatchbe.domain.gift.entity.Gift;
import com.example.overwatchbe.domain.gift.repository.GiftRepository;
import com.example.overwatchbe.domain.log.dto.GiftLogResponse;
import com.example.overwatchbe.domain.log.entity.GiftStatistics;
import com.example.overwatchbe.domain.log.repository.GiftStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftLogService {

    private final GiftStatisticsRepository giftStatisticsRepository;
    private final GiftRepository giftRepository;

    public List<GiftLogResponse> getGiftLogsByUserId(Long userId) {
        // GiftStatistics 조회
        GiftStatistics statistics = giftStatisticsRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Gift statistics not found for user: " + userId));

        // Gift 데이터 조회
        List<Gift> gifts = giftRepository.findAllBySender_UserIdOrReceiver_UserId(userId, userId);

        // Gift와 GiftStatistics 데이터를 병합하여 반환
        return gifts.stream()
                .map(gift -> new GiftLogResponse(
                        statistics.getUser().getUserId(),
                        statistics.getTotalGiftsSent(),
                        statistics.getTotalGiftsReceived(),
                        statistics.getLastGiftDate(),
                        gift.getGiftId(),
                        gift.getSender().getUserId(),
                        gift.getSender().getUsername(),
                        gift.getReceiver().getUserId(),
                        gift.getReceiver().getUsername(),
                        gift.getItem().getItemId(),
                        gift.getGiftedAt()
                ))
                .collect(Collectors.toList());
    }
}
