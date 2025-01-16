package com.example.smartTerrarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateSettingDto {
    private String name;
    private String description;
    private double temperature;
    private double moisture;
    private double waterOverWeek;
    private LocalTime irradiationStart;
    private LocalTime irradiationStop;
}
