package com.example.overwatchbe.domain.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rarity rarity;

    @Column(nullable = false)
    private int price;

    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false) // 외래 키 설정
    private Character character;

    @Column(columnDefinition = "TEXT") // description 컬럼 추가
    private String description;
}
