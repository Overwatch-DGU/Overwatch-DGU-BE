package com.example.overwatchbe.domain.shop.entity;

public enum Role {
    TANK("Tank"),
    DAMAGE("Damage"),
    SUPPORT("Support");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}