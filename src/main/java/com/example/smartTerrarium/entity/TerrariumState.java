package com.example.smartTerrarium.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "terrarium_state")
@Builder
@Setter
public class TerrariumState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "last_update")
    private Date lastUpdate;
    private double temperature;
    private double moisture;
    private boolean ventilation;
    private boolean irradiation;
}
