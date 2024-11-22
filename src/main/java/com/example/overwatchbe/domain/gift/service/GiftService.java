package com.example.overwatchbe.domain.gift.service;

import com.example.overwatchbe.domain.gift.dto.GiftRequest;
import com.example.overwatchbe.domain.gift.dto.GiftResponse;
import com.example.overwatchbe.domain.gift.entity.Gift;
import com.example.overwatchbe.domain.gift.repository.GiftRepository;
import com.example.overwatchbe.domain.shop.dto.CharacterResponse;
import com.example.overwatchbe.domain.shop.dto.ItemResponse;
import com.example.overwatchbe.domain.shop.entity.Item;
import com.example.overwatchbe.domain.shop.repository.ItemRepository;
import com.example.overwatchbe.domain.shop.repository.CharacterRepository;
import com.example.overwatchbe.domain.user.entity.User;
import com.example.overwatchbe.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final ItemRepository itemRepository;
    private final CharacterRepository characterRepository;

    private final GiftRepository giftRepository;
    private final UserRepository userRepository;

//    public GiftService(GiftRepository giftRepository, UserRepository userRepository, ItemRepository itemRepository, CharacterRepository characterRepository) {
//        this.giftRepository = giftRepository;
//        this.userRepository = userRepository;
//        this.itemRepository = itemRepository;
//        this.characterRepository = characterRepository;
//    }

    // 모든 캐릭터 조회
    public List<CharacterResponse> getAllCharacters() {
        return characterRepository.findAll().stream()
                .map(character -> new CharacterResponse(
                        character.getCharacterId(),
                        character.getName(),
                        character.getRole().getDisplayName(),
                        character.getImage(),
                        character.getDescription()
                ))
                .collect(Collectors.toList());
    }

    // 특정 캐릭터의 아이템 목록 조회
    public List<ItemResponse> getItemsByCharacter(Long characterId) {
        // 캐릭터가 존재하는지 확인
        characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found"));

        // 해당 캐릭터의 아이템 목록 반환
        return itemRepository.findAllByCharacter_CharacterId(characterId).stream()
                .map(item -> new ItemResponse(
                        item.getItemId(),
                        item.getName(),
                        item.getType(),
                        item.getRarity().getDisplayName(),
                        item.getPrice(),
                        item.getImage(),
                        item.getDescription()
                ))
                .collect(Collectors.toList());
    }

//    @Transactional
//    public GiftResponse sendGift(GiftRequest giftRequest) {
//        // Sender validation
//        Long senderId = giftRequest.getSenderId();
//        User sender = userRepository.findById(senderId)
//                .orElseThrow(() -> new RuntimeException("Sender not found"));
//
//        // Receiver validation
//        Long receiverId = giftRequest.getReceiverId();
//        User receiver = userRepository.findById(receiverId)
//                .orElseThrow(() -> new RuntimeException("Receiver not found"));
//
//        // Item validation
//        Long itemId = giftRequest.getItemId();
//        Item item = itemRepository.findById(itemId)
//                .orElseThrow(() -> new RuntimeException("Item not found"));
//
//        // Check if receiver already owns the item
//        boolean alreadyOwned = giftRepository.existsByReceiver_UserIdAndItem_ItemId(receiver.getUserId(), item.getItemId());
//        if (alreadyOwned) {
//            throw new RuntimeException("The receiver already owns this item");
//        }
//
//        // Check sender's coins
//        if (sender.getCoin() < item.getPrice()) {
//            throw new RuntimeException("Sender does not have enough coins");
//        }
//
//        // Deduct coins from sender
//        sender.setCoin(sender.getCoin() - item.getPrice());
//        userRepository.save(sender);
//
//        // Save gift record
//        Gift gift = Gift.builder()
//                .sender(sender)
//                .receiver(receiver)
//                .item(item)
//                .giftedAt(LocalDateTime.now())
//                .build();
//        giftRepository.save(gift);
//
//        // Return response
//        return new GiftResponse("Gift sent successfully", item.getItemId(), sender.getCoin());
//    }
    public GiftResponse sendGift(User sender, User receiver, Item item) {
        // Check if receiver already owns the item
        boolean alreadyOwned = giftRepository.existsByReceiver_UserIdAndItem_ItemId(receiver.getUserId(), item.getItemId());
        if (alreadyOwned) {
            return new GiftResponse("The receiver already owns this item", item.getItemId(), sender.getCoin());
        }

        // Deduct item price from sender's coins
        if (sender.getCoin() < item.getPrice()) {
            throw new IllegalStateException("Insufficient coins");
        }
        sender.setCoin(sender.getCoin() - item.getPrice());

        // Perform the gift transaction
        Gift gift = Gift.builder()
                .sender(sender)
                .receiver(receiver)
                .item(item)
                .giftedAt(LocalDateTime.now())
                .build();
        giftRepository.save(gift);

        // Return response with success message
        return new GiftResponse("Gift sent successfully", item.getItemId(), sender.getCoin());
    }
}