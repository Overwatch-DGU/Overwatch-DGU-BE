package com.example.overwatchbe.domain.log.entity;

import com.example.overwatchbe.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "gift_statistics")
public class GiftStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_gifts_sent", nullable = false)
    private int totalGiftsSent = 0;

    @Column(name = "total_gifts_received", nullable = false)
    private int totalGiftsReceived = 0;

    @Column(name = "last_gift_date")
    private LocalDateTime lastGiftDate;

    public void incrementGiftsSent(LocalDateTime giftDate) {
        this.totalGiftsSent++;
        this.lastGiftDate = giftDate;
    }
    public void incrementGiftsReceived(LocalDateTime giftDate) {
        this.totalGiftsReceived++;
        this.lastGiftDate = giftDate;
    }
}
