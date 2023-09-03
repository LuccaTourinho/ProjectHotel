package com.Hotels_System.ProjectHotel.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Quality{
    ECONOMIC(100),
    STANDARD(250),
    PREMIUM(500),
    LUXURY(1000);

    private final int basePrice;

    Quality(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
