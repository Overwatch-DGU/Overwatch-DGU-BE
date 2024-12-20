package com.example.overwatchbe.domain.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private com.example.overwatchbe.domain.user.entity.User user;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false, referencedColumnName = "item_id")
    private Item item;
}
