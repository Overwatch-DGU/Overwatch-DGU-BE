package com.example.overwatchbe.domain.shop.service;

import com.example.overwatchbe.domain.shop.dto.*;
import com.example.overwatchbe.domain.shop.entity.*;
import com.example.overwatchbe.domain.shop.repository.*;
import com.example.overwatchbe.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.lang.Character;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final CharacterRepository characterRepository;
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;

    public ShopService(CharacterRepository characterRepository, ItemRepository itemRepository,
                       InventoryRepository inventoryRepository, UserRepository userRepository) {
        this.characterRepository = characterRepository;
        this.itemRepository = itemRepository;
        this.inventoryRepository = inventoryRepository;
        this.userRepository = userRepository;
    }

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

    public List<ItemResponse> getItemsByCharacter(Long characterId, Long userId) {
//         Validate character existence
        characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found"));


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

    public ItemDetailResponse getItemDetail(Long itemId, Long userId) {
        // Fetch item and validate existence
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        boolean owned = inventoryRepository.existsByUser_UserIdAndItem_ItemId(userId, itemId);

        return new ItemDetailResponse(
                item.getItemId(),
                item.getName(),
                item.getType(),
                item.getRarity().getDisplayName(),
                item.getPrice(),
                item.getImage(),
                item.getDescription(),
                owned
        );
    }

//    public BuyResponse buyItem(BuyRequest buyRequest) {
//        // Fetch user and validate existence
//        var user = userRepository.findById(buyRequest.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Fetch item and validate existence
//        var item = itemRepository.findById(buyRequest.getItemId())
//                .orElseThrow(() -> new RuntimeException("Item not found"));
//
//        if (user.getCoin() < item.getPrice()) {
//            throw new RuntimeException("Not enough coins");
//        }
//
//        // Deduct coins and save the purchase
//        user.setCoin(user.getCoin() - item.getPrice());
//        userRepository.save(user);
//
//        var inventory = Inventory.builder()
//                .user(user)
//                .item(item)
//                .build();
//        inventoryRepository.save(inventory);
//
//        return new BuyResponse(user.getCoin(), "Item purchased successfully");
//    }
}
