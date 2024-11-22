package com.example.overwatchbe.domain.gift.service;

import com.example.overwatchbe.domain.shop.dto.CharacterResponse;
import com.example.overwatchbe.domain.shop.dto.ItemResponse;
import com.example.overwatchbe.domain.shop.entity.Item;
import com.example.overwatchbe.domain.shop.repository.ItemRepository;
import com.example.overwatchbe.domain.shop.entity.Character;
import com.example.overwatchbe.domain.shop.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final CharacterRepository characterRepository;
    private final ItemRepository itemRepository;

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
}
