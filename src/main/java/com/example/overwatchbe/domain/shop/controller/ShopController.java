package com.example.overwatchbe.domain.shop.controller;

import com.example.overwatchbe.domain.shop.dto.*;
import com.example.overwatchbe.domain.shop.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    // Get all characters
    @GetMapping("/characters")
    public ResponseEntity<List<CharacterResponse>> getAllCharacters() {
        return ResponseEntity.ok(shopService.getAllCharacters());
    }

    // Get items by character
    @GetMapping("/characters/{characterId}")
    public ResponseEntity<List<ItemResponse>> getItemsByCharacter(@PathVariable Long characterId, @RequestParam Long userId) {
        return ResponseEntity.ok(shopService.getItemsByCharacter(characterId, userId));
    }

    // Get item details
    @GetMapping("/items/{itemId}")
    public ResponseEntity<ItemDetailResponse> getItemDetail(@PathVariable Long itemId, @RequestParam Long userId) {
        return ResponseEntity.ok(shopService.getItemDetail(itemId, userId));
    }

    // Buy item
    @PostMapping("/buy")
    public ResponseEntity<BuyResponse> buyItem(@RequestBody BuyRequest buyRequest) {
        return ResponseEntity.ok(shopService.buyItem(buyRequest));
    }
}
