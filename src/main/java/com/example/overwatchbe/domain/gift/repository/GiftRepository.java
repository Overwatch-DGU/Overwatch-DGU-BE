package com.example.overwatchbe.domain.gift.repository;

import com.example.overwatchbe.domain.gift.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    boolean existsByReceiver_UserIdAndItem_ItemId(Long receiverId, Long itemId);
    // 추가: 특정 사용자가 보낸/받은 모든 Gift 조회
    List<Gift> findAllBySender_UserIdOrReceiver_UserId(Long senderId, Long receiverId);

}