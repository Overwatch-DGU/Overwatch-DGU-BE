package com.example.overwatchbe.domain.gift.controller;

import com.example.overwatchbe.domain.gift.dto.GiftItemResponse;
import com.example.overwatchbe.domain.gift.dto.GiftRequest;
import com.example.overwatchbe.domain.gift.dto.GiftResponse;
import com.example.overwatchbe.domain.gift.service.GiftService;
import com.example.overwatchbe.domain.shop.dto.CharacterResponse;
import com.example.overwatchbe.domain.shop.dto.ItemResponse;
import com.example.overwatchbe.domain.shop.entity.Item;
import com.example.overwatchbe.domain.shop.repository.ItemRepository;
import com.example.overwatchbe.domain.user.entity.User;
import com.example.overwatchbe.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gifts")
@RequiredArgsConstructor
public class GiftController {

    private final GiftService giftService;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

//    public GiftController(GiftService giftService, UserRepository userRepository, ItemRepository itemRepository) {
//        this.giftService = giftService;
//        this.userRepository = userRepository;
//        this.itemRepository = itemRepository;
//    }

    // 캐릭터 목록 조회
    @GetMapping("/characters")
    public ResponseEntity<List<CharacterResponse>> getAllCharacters() {
        return ResponseEntity.ok(giftService.getAllCharacters());
    }

    // 특정 캐릭터의 아이템 목록 조회
    @GetMapping("/characters/{characterId}")
    public ResponseEntity<List<GiftItemResponse>> getItemsByCharacter(@PathVariable Long characterId) {
        return ResponseEntity.ok(giftService.getItemsByCharacter(characterId));
    }

    //특정 사용자에게 아이템 선물
//    @PostMapping("/send")
//    public ResponseEntity<GiftResponse> sendGift(@RequestBody GiftRequest giftRequest) {
//        GiftResponse response = giftService.sendGift(giftRequest); // GiftRequest 전달
//        return ResponseEntity.ok(response);
//    }
    @PostMapping("/send")
    public ResponseEntity<Object> sendGift(@RequestBody GiftRequest request) {
        // Fetch sender, receiver, and item from DB
        User sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        User receiver = userRepository.findByEmail(request.getReceiverEmail())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        // Call the service
        GiftResponse response = giftService.sendGift(sender, receiver, item);

        // Return appropriate HTTP status based on success or failure
        if ("Gift sent successfully".equals(response.getMessage())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
