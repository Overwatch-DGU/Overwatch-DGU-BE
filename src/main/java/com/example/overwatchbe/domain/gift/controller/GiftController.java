package com.example.overwatchbe.domain.gift.controller;

import com.example.overwatchbe.domain.gift.dto.GiftRequest;
import com.example.overwatchbe.domain.gift.dto.GiftResponse;
import com.example.overwatchbe.domain.gift.service.GiftService;
import com.example.overwatchbe.domain.shop.dto.CharacterResponse;
import com.example.overwatchbe.domain.shop.dto.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gifts")
@RequiredArgsConstructor
public class GiftController {

    private final GiftService giftService;

    // 캐릭터 목록 조회
    @GetMapping("/characters")
    public ResponseEntity<List<CharacterResponse>> getAllCharacters() {
        return ResponseEntity.ok(giftService.getAllCharacters());
    }

    // 특정 캐릭터의 아이템 목록 조회
    @GetMapping("/characters/{characterId}")
    public ResponseEntity<List<ItemResponse>> getItemsByCharacter(@PathVariable Long characterId) {
        return ResponseEntity.ok(giftService.getItemsByCharacter(characterId));
    }
}
