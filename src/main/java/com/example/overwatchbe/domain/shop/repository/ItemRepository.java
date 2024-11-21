package com.example.overwatchbe.domain.shop.repository;

import com.example.overwatchbe.domain.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByCharacter_CharacterId(Long characterId);
}
