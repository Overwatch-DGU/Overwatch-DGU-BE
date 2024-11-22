package com.example.overwatchbe.domain.log.controller;

import com.example.overwatchbe.domain.log.dto.GiftLogResponse;
import com.example.overwatchbe.domain.log.service.GiftLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final GiftLogService giftLogService;

    public LogController(GiftLogService giftLogService) {
        this.giftLogService = giftLogService;
    }

    @GetMapping("/gifts")
    public List<GiftLogResponse> getGiftLogs(@RequestParam Long userId) {
        return giftLogService.getGiftLogsByUserId(userId);
    }
}
