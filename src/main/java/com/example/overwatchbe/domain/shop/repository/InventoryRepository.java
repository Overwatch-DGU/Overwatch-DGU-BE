package com.example.overwatchbe.domain.shop.repository;

import com.example.overwatchbe.domain.shop.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsByUser_UserIdAndItem_ItemId(Long userId, Long itemId);
}
