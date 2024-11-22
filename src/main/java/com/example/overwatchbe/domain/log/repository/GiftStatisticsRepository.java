package com.example.overwatchbe.domain.log.repository;

import com.example.overwatchbe.domain.gift.entity.Gift;
import com.example.overwatchbe.domain.log.entity.GiftStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GiftStatisticsRepository extends JpaRepository<GiftStatistics, Long> {
    Optional<GiftStatistics> findByUser_UserId(Long userId);
}