package com.Hotels_System.ProjectHotel.domain;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class PrimaryKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_ID")
    private Long id;
}
