package com.example.overwatchbe.domain.gift.repository;

import com.example.overwatchbe.domain.gift.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    List<Gift> findAllByReceiver_UserId(Long receiverId);
}
