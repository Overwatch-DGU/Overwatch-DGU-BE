package com.example.overwatchbe.domain.shop.repository;

import com.example.overwatchbe.domain.shop.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
