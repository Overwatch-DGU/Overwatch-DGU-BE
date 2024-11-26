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
    @Column(name = "item_id")
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

//    @Column
//    private String image; //처음에 string으로 했으나, 길이 제한걸려서 변경.
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false, referencedColumnName = "character_id") // 외래 키 설정
    private Character character;

    @Column(columnDefinition = "TEXT") // description 컬럼 추가
    private String description;
}
