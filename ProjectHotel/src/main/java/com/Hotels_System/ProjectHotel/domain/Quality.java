package com.Hotels_System.ProjectHotel.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Quality {
    ECONOMIC,
    STANDARD,
    PREMIUM,
    LUXURY
}
