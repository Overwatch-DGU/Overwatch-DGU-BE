package com.example.overwatchbe.domain.gift.repository;

import com.example.overwatchbe.domain.gift.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    boolean existsByReceiver_UserIdAndItem_ItemId(Long receiverId, Long itemId);
}
